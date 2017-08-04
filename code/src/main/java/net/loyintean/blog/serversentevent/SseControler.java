/**
 * Copyright(c) 2011-2017 by  Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.serversentevent;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/msg")
public class SseControler extends HttpServlet {

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
        response.setContentType("text/event-stream;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");

        try (PrintWriter out = response.getWriter()) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(10000);
                String msg = "测试信息:" + new Date();
                out.print("data: " + msg + "\n");
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
