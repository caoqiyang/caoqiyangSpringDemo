package cn.project.web.servlet;

import cn.project.dao.UserDao;
import cn.project.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        //获取表单信息
        String name = req.getParameter("name");
        String album = req.getParameter("album");
        String checkCodeText = req.getParameter("checkCodeText");
        // 获取生成的验证码，获取之后删除！！！
        HttpSession session = req.getSession();
        String sessionCode = (String) session.getAttribute("sessionCode");
        session.removeAttribute("sessionCode");
        //忽略大小写
        if(sessionCode != null && sessionCode.equalsIgnoreCase(checkCodeText)){
            //验证码相同
            //新建对象
            User loginUser = new User();
            loginUser.setName(name);
            loginUser.setAlbum(album);
            // Dao的Login方法
            UserDao dao = new UserDao();
            try {
                User user = dao.login(loginUser);
                if(user == null){
                    // 登陆失败
                    System.out.println("登录失败");
                    req.getRequestDispatcher("/loginFail").forward(req,resp);
                }else{
                    // 登陆成功, 储存数据
                    req.setAttribute("user", user);
                    req.getRequestDispatcher("/loginSuccess").forward(req,resp);
                    System.out.println("登录成功......name: " + name + " album: "+album );
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            //验证码不同
            //在登陆页面加载错误信息
            req.setAttribute("error", "验证码错误");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
