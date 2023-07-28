package net.perry.forum.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "categoryServlet", urlPatterns = { "/category" })
public class CategoryServlet extends BaseServlet {
    /**
     * 返回全部分类
     * 
     * @param req
     * @param resp
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) {
        //TODO
    }
}
