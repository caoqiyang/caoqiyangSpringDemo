package cn.project.utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {

    // 全是静态方法和变量
    // 1 获取配置文件 2 连接 3 释放资源
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        try {
            Properties pro = new Properties();

            pro.load(new FileReader("D:\\java\\JavaWeb\\day1_tomcat\\src\\main\\java\\cn\\mysql.properties")); // 路径有问题 也可以用ClassLoader

            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");

            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Connection conn, Statement stmt) throws SQLException {
        if(stmt != null){
            stmt.close();
        }
        if(conn != null){
            conn.close();
        }
    }
}
