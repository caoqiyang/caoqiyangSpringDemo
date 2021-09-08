package cn.project.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "/check")
public class CheckCodeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int width = 100, height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //美化图片
        Graphics g = image.getGraphics();
        // 填充色
        g.setColor(Color.pink);
        g.fillRect(0, 0, width, height);
        //背景
        g.setColor(Color.blue);
        g.drawRect(0, 0, width - 1, height - 1);
        // 写字母
        String str = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        int len = str.length();
        Random ran = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 1; i++){
            char ch = str.charAt(ran.nextInt(len));
            sb.append(ch);
            g.drawString(ch + "", 20 + i * 20, 25);
        }
        req.getSession().setAttribute("sessionCode", sb.toString());

        // 画干扰线
        g.setColor(Color.green);
        for(int i = 0; i < 5; i++){
            int x1 = ran.nextInt(width), x2 = ran.nextInt(width);
            int y1 = ran.nextInt(height), y2 = ran.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }

        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
