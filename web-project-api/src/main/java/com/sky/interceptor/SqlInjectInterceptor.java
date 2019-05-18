package com.sky.interceptor;

import java.util.Map;
import java.util.Properties;

import com.sky.core.model.BaseModel;
import com.sky.model.SystemUser;
import com.sky.tools.UserUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

/**
 * 拦截器作用：给各实体对象在增加、修改时，自动添加操作属性信息。
 */
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class SqlInjectInterceptor implements Interceptor {

    private static final Log log = LogFactory.getLog(SqlInjectInterceptor.class);

    @Override
    @SuppressWarnings("unchecked")
    public Object intercept(Invocation invocation) throws Throwable {
        // 根据签名指定的args顺序获取具体的实现类
        // 1. 获取MappedStatement实例, 并获取当前SQL命令类型
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType commandType = ms.getSqlCommandType();

        // 2. 获取当前正在被操作的类, 有可能是Java Bean, 也可能是普通的操作对象, 比如普通的参数传递
        // 普通参数, 即是 @Param 包装或者原始 Map 对象, 普通参数会被 Mybatis 包装成 Map 对象
        // 即是 org.apache.ibatis.binding.MapperMethod$ParamMap
        Object parameter = invocation.getArgs()[1];
        // 获取拦截器指定的方法类型, 通常需要拦截 update
        String methodName = invocation.getMethod().getName();

        // 3. 获取当前用户信息
        SystemUser userEntity = UserUtil.getCurrentUser();
        // 默认测试参数值
        String oparator = "" ;
        if (userEntity != null) {
            oparator = userEntity.getUserName();
        }
        if (parameter instanceof BaseModel) {
            // 4. 实体类
            BaseModel baseModel = (BaseModel) parameter;
            if (methodName.equals("update")) {
                if (commandType.equals(SqlCommandType.INSERT)) {
                    baseModel.setCreateUser(oparator);
                } else if (commandType.equals(SqlCommandType.UPDATE)) {
                    baseModel.setUpdateUser(oparator);
                }
            }
        } else if (parameter instanceof Map) {
            // 5. @Param 等包装类
            // 更新时指定某些字段的最新数据值
            if (commandType.equals(SqlCommandType.UPDATE)) {
                // 遍历参数类型, 检查目标参数值是否存在对象中, 该方式需要应用编写有一些统一的规范
                // 否则均统一为实体对象, 就免去该重复操作
                Map map = (Map) parameter;
                for (Object value : map.values()) {
                    if(value instanceof BaseModel){
                        BaseModel baseModel = (BaseModel) value;
                        baseModel.setUpdateUser(oparator);
                    }
                }
            }
        }
        // 6. 均不是需要被拦截的类型, 不做操作
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
