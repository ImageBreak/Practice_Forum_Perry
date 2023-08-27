package net.perry.forum.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.perry.forum.domain.Reply;
import net.perry.forum.domain.Topic;
import net.perry.forum.domain.User;
import net.perry.forum.dto.PageDTO;
import net.perry.forum.service.CategoryService;
import net.perry.forum.service.TopicService;
import net.perry.forum.service.impl.CategoryServiceImpl;
import net.perry.forum.service.impl.TopicServiceImpl;

@WebServlet(name = "topicServlet", urlPatterns = { "/topic" })
public class TopicServlet extends BaseServlet {

    TopicService topicService = new TopicServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    /**
     * 默认分页大小
     */
    private static final int pageSize = 3;

    public void list(HttpServletRequest req, HttpServletResponse resp) {

        int cId = Integer.parseInt(req.getParameter("c_id"));

        // 默认第一页
        int page = 1;

        String currentPage = req.getParameter("page");
        if (currentPage != null && currentPage != "") {
            page = Integer.parseInt(currentPage);
        }

        PageDTO<Topic> pageDTO = topicService.listTopicPageByCid(cId, page, pageSize);

        req.getSession().setAttribute("categoryList", categoryService.list());
        req.getSession().setAttribute("currentCategoryId", cId);
        req.setAttribute("topicPage", pageDTO);
        try {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 查看主题的全部回复
     * 
     * @param req
     * @param resp
     */
    public void findDetailById(HttpServletRequest req, HttpServletResponse resp) {
        // 获取topic的id
        int topicId = Integer.parseInt(req.getParameter("topic_id"));

        // 默认第一页
        int page = 1;

        String currentPage = req.getParameter("page");
        if (currentPage != null && currentPage != "") {
            page = Integer.parseInt(currentPage);
        }

        //处理浏览量递增，如果是同个session内，只算一次
        String sessionReadKey = "is_read"+topicId;
        Boolean isRead = (Boolean) req.getSession().getAttribute(sessionReadKey);

        if(isRead == null){
            req.getSession().setAttribute(sessionReadKey, true);
            //新增一个pv
            topicService.addOnePv(topicId);
        }

        Topic topic = topicService.findById(topicId);
        PageDTO<Reply> pageDTO = topicService.findReplyPageByTopicId(topicId, page, pageSize);

        req.getSession().setAttribute("topic", topic);
        req.setAttribute("replyPage", pageDTO);
        try {
            req.getRequestDispatcher("/topic_detail.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 发布主题
     * 
     * @param req
     * @param resp
     */
    public void addTopic(HttpServletRequest req, HttpServletResponse resp) {
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        try {
            if (loginUser == null) {
            req.setAttribute("msg", "请登录");
            // 页面跳转
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
        } else {
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            int cId = Integer.parseInt(req.getParameter("c_id"));

            int rows = topicService.addTopic(loginUser, title, content, cId);

            if (rows == 1) {
                // 发布主题成功
                req.getRequestDispatcher("topic?method=list&c_id="+cId).forward(req, resp);
            } else {
                // 发布主题失败
                req.setAttribute("msg", "发布失败喽，杂鱼");
                req.getRequestDispatcher("publish.jsp").forward(req, resp);
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回复主题
     * @param req
     * @param resp
     */
    public void replyByTopicId(HttpServletRequest req, HttpServletResponse resp){
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        try {
            if (loginUser == null) {
            req.setAttribute("msg", "请登录");
            // 页面跳转
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
        } else {
            int topicId = Integer.parseInt(req.getParameter("topic_id"));
            String content = req.getParameter("content");

            int rows = topicService.replyByTopicId(loginUser, topicId, content);

            if (rows == 1) {
                // 回复成功
                req.getRequestDispatcher("topic?method=findDetailById&topic_id="+topicId).forward(req, resp);
            } else {
                // 回复失败
                req.setAttribute("msg", "回复失败喽，杂鱼");
                req.getRequestDispatcher("reply.jsp").forward(req, resp);
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
