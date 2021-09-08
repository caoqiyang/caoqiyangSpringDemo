package cn.project.test;

import org.junit.Test;
import cn.project.dao.UserDao;
import cn.project.domain.User;

import java.sql.SQLException;

public class UserDaoTest {

    @Test
    public void testLogin() throws SQLException {
        User user = new User();
        user.setName("exile");
        user.setAlbum("folklore");

        UserDao dao = new UserDao();
        System.out.println(dao.login(user));
    }
}
