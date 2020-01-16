package com.sky.core.utils;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyDBUtils {
    private static String className = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/stock_data?useUnicode=true&characterEncoding=utf-8";
    private static String user = "root";
    private static String password = "123456";
    private static QueryRunner queryRunner = new QueryRunner();

    // 拒绝new一个实例
    private MyDBUtils() {

    }

    static {// 调用该类时既注册驱动
        try {
            Class.forName(className);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //查询
    public static List<String> executeQuerySql(String sql) {
        List<String> result = new ArrayList<String>();
        try {
            List<Object[]> requstList = queryRunner.query(getConnection(), sql,
                    new ArrayListHandler(new BasicRowProcessor() {
                        @Override
                        public <Object> List<Object> toBeanList(ResultSet rs, Class<Object> type) throws SQLException {
                            return super.toBeanList(rs, type);
                        }
                    })
            );
            for (Object[] objects : requstList) {
                result.add(objects[0].toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //这个方法可以执行一些更新或者新增的sql语句或者删除
    public static void update(String sql, Object... params) {
        try {
            Connection connection = getConnection();
            queryRunner.update(connection, sql, params);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取连接
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
