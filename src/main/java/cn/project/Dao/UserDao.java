package cn.project.dao;

import cn.project.domain.User;
import cn.project.utils.DruidUtils;
import java.sql.*;

public class UserDao {

    public User login(User loginUser) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        //MyDataSource myDataSource = new MyDataSource();
        try {
            conn = DruidUtils.getConnection(); // 可以有事务
//        Statement stmt = conn.createStatement();
            String sql = "select * from TaylorSwift where name = ? and album = ?";
            // select * from TaylorSwift where name = "exile" and album = "folklore"
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginUser.getName());
            pstmt.setString(2, loginUser.getAlbum());
            rs = pstmt.executeQuery();
            while(rs.next()){
                count++;
//            String name=rs.getString("name");
//            String album=rs.getString("album");
//            System.out.println("name：" + name + ", album: " + album);
//            System.out.println("-----------------------");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            DruidUtils.close(pstmt, conn);
        }
        if(count == 0) return null;
        return loginUser;
    }
}
