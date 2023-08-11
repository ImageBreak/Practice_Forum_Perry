package net.perry.forum.dao;

import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import net.perry.forum.domain.Topic;
import net.perry.forum.util.DataSourceUtil;

public class TopicDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());
    // 开启驼峰映射
    private BeanProcessor beanProcessor = new GenerousBeanProcessor();
    private RowProcessor processor = new BasicRowProcessor(beanProcessor);

    /**
     * 根据cId查询总行数
     * 
     * @param cId
     * @return
     */
    public int countTotalTopicByCid(int cId) {
        String sql = "select count(*) from topic where c_id=? and `delete`=0";
        Long count = null;
        try {
            count = (Long) queryRunner.query(sql, new ScalarHandler<>(), cId);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return count.intValue();
    }

    /**
     * 分页查询
     * 
     * @param cId
     * @param from
     * @param pageSize
     * @return
     */
    public List<Topic> findListByCid(int cId, int from, int pageSize) {
        String sql = "select * from topic where c_id=? and `delete`=0 order by update_time desc limit ?,?";
        List<Topic> list = null;
        try {
            list = queryRunner.query(sql, new BeanListHandler<>(Topic.class, processor), cId, from, pageSize);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据topicId找topic
     * 
     * @param topicId
     * @return
     */
    public Topic findById(int topicId) {
        String sql = "select * from topic where id = ?";
        Topic topic = null;
        try {
            topic = queryRunner.query(sql, new BeanHandler<>(Topic.class, processor), topicId);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return topic;
    }

    public int save(Topic topic) throws Exception {
        String sql = "insert into topic(c_id,title,content,pv,user_id,username,user_img,create_time,update_time,hot,`delete`) values(?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {
                topic.getcId(),
                topic.getTitle(),
                topic.getContent(),
                topic.getPv(),
                topic.getUserId(),
                topic.getUsername(),
                topic.getUserImg(),
                topic.getCreateTime(),
                topic.getUpdateTime(),
                topic.getHot(),
                topic.getDelete()
        };

        int i = 0;
        try {
            i = queryRunner.update(sql, params);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new Exception();
        }
        return i;
    }

}
