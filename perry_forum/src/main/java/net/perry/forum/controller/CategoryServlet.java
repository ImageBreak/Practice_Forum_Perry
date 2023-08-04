package net.perry.forum.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.perry.forum.domain.Category;
import net.perry.forum.service.CategoryService;
import net.perry.forum.service.impl.CategoryServiceImpl;

@WebServlet(name = "categoryServlet", urlPatterns = { "/category" })
public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryServiceImpl();
    /**
     * 返回全部分类
     * 
     * @param req
     * @param resp
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) {
        List<Category> list = categoryService.list();
        req.setAttribute("categoryList", list);
    }
}
