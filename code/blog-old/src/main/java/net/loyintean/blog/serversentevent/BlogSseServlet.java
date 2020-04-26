/**
 * Copyright(c) 2011-2017 by  Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.serversentevent;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author linjun
 * @since 2017年8月1日
 */
@WebServlet("/")
public class BlogSseServlet extends HttpServlet {

    private static final long serialVersionUID = -8248240713081774034L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) {

        System.out.println("===================================");
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) {
        System.out.println("--------------------------------");

        try (PrintWriter out = response.getWriter()) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(10000);
                out.print("服务已启动\n");
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}