package net.perry.forum.service;

import net.perry.forum.domain.Topic;
import net.perry.forum.dto.PageDTO;

public interface TopicService {

    PageDTO<Topic> listTopicPageByCid(int cId, int page, int pageSize);
}
