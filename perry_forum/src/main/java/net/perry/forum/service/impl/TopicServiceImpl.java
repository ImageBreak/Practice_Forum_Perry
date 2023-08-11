package net.perry.forum.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import net.perry.forum.dao.CategoryDao;
import net.perry.forum.dao.ReplyDao;
import net.perry.forum.dao.TopicDao;
import net.perry.forum.domain.Category;
import net.perry.forum.domain.Reply;
import net.perry.forum.domain.Topic;
import net.perry.forum.domain.User;
import net.perry.forum.dto.PageDTO;
import net.perry.forum.service.TopicService;

public class TopicServiceImpl implements TopicService{

    private TopicDao topicDao = new TopicDao();
    private ReplyDao replyDao = new ReplyDao();
    private CategoryDao categoryDao = new CategoryDao();

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

    @Override
    public int addTopic(User loginUser, String title, String content, int cId) {
        Category category = categoryDao.findById(cId);
        if(category == null){
            return 0;
        }

        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setContent(content);
        topic.setCreateTime(LocalDateTime.now());
        topic.setUpdateTime(LocalDateTime.now());
        topic.setPv(1);
        topic.setDelete(0);
        topic.setUserId(loginUser.getId());
        topic.setUsername(loginUser.getUsername());
        topic.setUserImg(loginUser.getImg());
        topic.setcId(cId);
        topic.setHot(0);

        int rows = 0;
        try {
            rows = topicDao.save(topic);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rows;
    }
    
}
