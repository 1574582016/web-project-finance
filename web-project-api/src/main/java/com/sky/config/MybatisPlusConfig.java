package com.sky.config;

import com.baomidou.mybatisplus.mapper.ISqlInjector;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.baomidou.mybatisplus.plugins.CachePaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.plugins.SqlExplainInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ThinkPad on 2018/10/5.
 */
@Configuration
@MapperScan("com.sky.mapper*")
public class MybatisPlusConfig {

    @Value("${mybatis-plus.performance.max-time}")
    private long maxTime;

    @Value("${mybatis-plus.performance.format-enabled}")
    private Boolean isFormat;

    @Value("${mybatis-plus.performance.write-in-log-enabled}")
    private Boolean isWriteInLog;

    @Value("${mybatis-plus.sqlexplain.stop-proceed-enabled}")
    private Boolean isStopProceed;

    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MyMetaObjectHandler();
    }


    /**
     * mybatis-plus 性能sql分析插件【生产环境可以关闭】
     * <desc>
     * 格式化sql执行的时间
     * </desc>
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setFormat(isFormat);
        performanceInterceptor.setMaxTime(maxTime);
        performanceInterceptor.setWriteInLog(isWriteInLog);
        return performanceInterceptor;
    }


    /**
     * mybatis-plus 执行sql分析插件【生产环境可以关闭】
     * <desc>
     * 阻止全表删除/更新操作
     * </desc>
     */
    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor() {
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        sqlExplainInterceptor.setStopProceed(isStopProceed);
        return sqlExplainInterceptor;
    }

    /**
     * mybatis-plus分页插件<br>
     */
    @Bean
    public CachePaginationInterceptor paginationInterceptor() {
        CachePaginationInterceptor paginationInterceptor = new CachePaginationInterceptor();
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    /**
     * 注入sql注入器
     */
//    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

}
