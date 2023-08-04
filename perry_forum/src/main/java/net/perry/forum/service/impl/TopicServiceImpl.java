package net.perry.forum.service.impl;

import java.util.List;

import net.perry.forum.dao.TopicDao;
import net.perry.forum.domain.Topic;
import net.perry.forum.dto.PageDTO;
import net.perry.forum.service.TopicService;

public class TopicServiceImpl implements TopicService{

    private TopicDao topicDao = new TopicDao();

    @Override
    public PageDTO<Topic> listTopicPageByCid(int cId, int page, int pageSize) {

        //查询总记录数
        int totalRecordNum = topicDao.countTotalTopicByCid(cId);

        int from = (page - 1) * pageSize;

        //分页查询
        List<Topic> topicList = topicDao.findListByCid(cId, from ,pageSize);

        PageDTO<Topic> pageDTO = new PageDTO<>(page, pageSize, totalRecordNum);
        pageDTO.setList(topicList);
        return pageDTO;
    }
    
}
