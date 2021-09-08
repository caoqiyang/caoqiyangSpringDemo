package cn.project.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import cn.project.domain.User;

@WebServlet(urlPatterns = "/loginSuccess")
public class loginsSuccess extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取信息
        User user = (User) req.getAttribute("user");

        // 输出内容
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write("<h1>呵呵Glad you did it...Looks like you do have listened to "
                                + user.getName() + " from " + user.getAlbum() + " !</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
