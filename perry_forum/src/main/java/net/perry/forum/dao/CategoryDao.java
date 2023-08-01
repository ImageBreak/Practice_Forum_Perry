package net.perry.forum.dao;

import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import net.perry.forum.domain.Category;
import net.perry.forum.util.DataSourceUtil;

public class CategoryDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());
    // 开启驼峰映射
    private BeanProcessor beanProcessor = new GenerousBeanProcessor();
    private RowProcessor processor = new BasicRowProcessor(beanProcessor);

    /**
     * 根据id找分类
     * @param id
     * @return
     */
    public Category findById(int id) {
        String sql = "select * from category where id = ?";
        Category category = null;
        try {
            category = queryRunner.query(sql, new BeanHandler<>(Category.class, processor), id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return category;
    }

    /**
     * 返回分类列表
     * @return
     */
    public List<Category> list() {
        String sql = "select * from category";
        List<Category> list = null;
        try {
            list = queryRunner.query(sql, new BeanListHandler<>(Category.class, processor));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }
}
