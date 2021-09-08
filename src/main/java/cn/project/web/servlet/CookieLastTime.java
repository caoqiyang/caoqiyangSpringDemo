package cn.project.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(value = "/cookie")
public class CookieLastTime extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        Cookie[] cookies = request.getCookies();
        Boolean flag = false; // 默认没有cookie
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie: cookies){
                String name = cookie.getName();
                if("lastTime".equals(name)){
                    // 有cookie，不是第一次访问
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                    String str_date = sdf.format(date);
                    str_date = URLEncoder.encode(str_date, "utf-8");
                    String value = cookie.getValue();
                    cookie.setValue(str_date); //更新cookie
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(cookie);

                    value = URLEncoder.encode(value, "utf-8");
                    response.getWriter().write("<h1>你上次的访问时间为：" + value + "</h1>");
                    flag = true;

                    break; // 只循环第一个
                }
            }
        }
        if(flag == false){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String str_date = sdf.format(date);
            Cookie cookie = new Cookie("lastTime", str_date);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);

            response.getWriter().write("<h1>第一次访问 你好！</h1>");
        }
    }
}
