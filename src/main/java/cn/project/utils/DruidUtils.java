package cn.project.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DruidUtils {

    private static DataSource ds;
    static {
        //配置
        Properties pro = new Properties();
        try {
            pro.load(new FileReader("D:\\java\\JavaWeb\\day1_tomcat\\src\\main\\java\\cn\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 获取连接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    // 释放资源
    public static void close(Statement stmt, Connection conn) throws SQLException {
        if(stmt != null){
            stmt.close();
        }
        if(conn != null){
            conn.close();
        }
    }

    public static DataSource getDataSource(){
        return ds;
    }
}
