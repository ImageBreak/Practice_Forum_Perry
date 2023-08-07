package net.perry.forum.service.impl;

import java.util.List;

import net.perry.forum.dao.ReplyDao;
import net.perry.forum.dao.TopicDao;
import net.perry.forum.domain.Reply;
import net.perry.forum.domain.Topic;
import net.perry.forum.dto.PageDTO;
import net.perry.forum.service.TopicService;

public class TopicServiceImpl implements TopicService{

    private TopicDao topicDao = new TopicDao();
    private ReplyDao replyDao = new ReplyDao();

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

    @Override
    public Topic findById(int topicId) {
        return topicDao.findById(topicId);
    }

    @Override
    public PageDTO<Reply> findReplyPageByTopicId(int topicId, int page, int pageSize) {

        //查询总的回复
        int totalRecordNum = replyDao.countTotalReplyByTopicId(topicId);
        int from = (page - 1) * pageSize;
         //分页查询
        List<Reply> replyList = replyDao.findListByTopicId(topicId, from ,pageSize);

        PageDTO<Reply> pageDTO = new PageDTO<>(page, pageSize, totalRecordNum);
        pageDTO.setList(replyList);
        return pageDTO;
    }
    
}
