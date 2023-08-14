package net.perry.forum.service;

import net.perry.forum.domain.Reply;
import net.perry.forum.domain.Topic;
import net.perry.forum.domain.User;
import net.perry.forum.dto.PageDTO;

public interface TopicService {

    PageDTO<Topic> listTopicPageByCid(int cId, int page, int pageSize);

    Topic findById(int topicId);

    PageDTO<Reply> findReplyPageByTopicId(int topicId, int page, int pagesize);

    int addTopic(User loginUser, String title, String content, int cId);

    int replyByTopicId(User loginUser, int topicId, String content);

    void addOnePv(int topicId);
}
