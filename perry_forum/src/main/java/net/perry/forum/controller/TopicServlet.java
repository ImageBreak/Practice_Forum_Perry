package net.perry.forum.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.perry.forum.domain.Topic;
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
}
