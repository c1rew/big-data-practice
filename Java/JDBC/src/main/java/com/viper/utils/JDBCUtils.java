package com.viper.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 操作数据库工具类
 *
 * @author c1rew
 * @date 2020-12-27 13:40
 */

public class JDBCUtils {

    /**
     * 获取数据库连接
     *
     * @return 返回数据库连接
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        // 读取配置文件
        InputStream inputStream = JDBCUtils.class.getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        prop.load(inputStream);

        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");
        String driverClass = prop.getProperty("driverClass");

        // 加载驱动
        Class.forName(driverClass);

        // 获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * 关闭资源
     * @param conn
     * @param ps
     */
    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
