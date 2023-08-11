package net.perry.forum.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.perry.forum.domain.Reply;
import net.perry.forum.domain.Topic;
import net.perry.forum.domain.User;
import net.perry.forum.dto.PageDTO;
import net.perry.forum.service.TopicService;
import net.perry.forum.service.impl.TopicServiceImpl;

@WebServlet(name = "topicServlet", urlPatterns = { "/topic" })
public class TopicServlet extends BaseServlet {

    TopicService topicService = new TopicServiceImpl();
    /**
     * 默认分页大小
     */
    private static final int pageSize = 2;

    public void list(HttpServletRequest req, HttpServletResponse resp) {

        int cId = Integer.parseInt(req.getParameter("c_id"));

        // 默认第一页
        int page = 1;

        String currentPage = req.getParameter("page");
        if (currentPage != null && currentPage != "") {
            page = Integer.parseInt(currentPage);
        }

        PageDTO<Topic> pageDTO = topicService.listTopicPageByCid(cId, page, pageSize);

        req.setAttribute("topicPage", pageDTO);
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

        Topic topic = topicService.findById(topicId);
        PageDTO<Reply> pageDTO = topicService.findReplyPageByTopicId(topicId, page, pageSize);

        req.setAttribute("topic", topic);
        req.setAttribute("replyPage", pageDTO);

    }

    /**
     * 发布主题
     * 
     * @param req
     * @param resp
     */
    public void addTopic(HttpServletRequest req, HttpServletResponse resp) {
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            req.setAttribute("msg", "请登录");
            // 页面跳转 TODO
        } else {
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            int cId = Integer.parseInt(req.getParameter("c_id"));

            int rows = topicService.addTopic(loginUser, title, content, cId);

            if (rows == 1) {
                // 发布主题成功
            } else {
                // 发布主题失败
            }
        }
    }

    /**
     * 回复主题
     * @param req
     * @param resp
     */
    public void replyByTopicId(HttpServletRequest req, HttpServletResponse resp){
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            req.setAttribute("msg", "请登录");
            // 页面跳转 TODO
        } else {
            int topicId = Integer.parseInt(req.getParameter("topic_id"));
            String content = req.getParameter("content");

            int rows = topicService.replyByTopicId(loginUser, topicId, content);

            if (rows == 1) {
                // 回复成功
            } else {
                // 回复失败
            }
        }
    }
}
