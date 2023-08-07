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

import net.perry.forum.domain.Category;
import net.perry.forum.domain.Reply;
import net.perry.forum.domain.Topic;
import net.perry.forum.util.DataSourceUtil;

public class ReplyDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());
    // 开启驼峰映射
    private BeanProcessor beanProcessor = new GenerousBeanProcessor();
    private RowProcessor processor = new BasicRowProcessor(beanProcessor);

    /**
     * 根据topicId查询回复总数
     * @param topicId
     * @return
     */
    public int countTotalReplyByTopicId(int topicId) {
        String sql = "select count(*) from reply where topic_id=? and `delete`=0";
        Long count = null;
        try {
            count = (Long) queryRunner.query(sql, new ScalarHandler<>(), topicId);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return count.intValue();
    }

    /**
     * 分页查询
     * @param topicId
     * @param from
     * @param pageSize
     * @return
     */
    public List<Reply> findListByTopicId(int topicId, int from, int pageSize) {
        String sql = "select * from reply where topic_id=? and `delete`=0 order by create_time asc limit ?,?";
        List<Reply> list = null;
        try {
            list = queryRunner.query(sql, new BeanListHandler<>(Reply.class, processor), topicId, from, pageSize);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 返回分类列表
     * @return
     */
    public List<Reply> list() {
        String sql = "select * from reply";
        List<Reply> list = null;
        try {
            list = queryRunner.query(sql, new BeanListHandler<>(Reply.class, processor));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }
}
