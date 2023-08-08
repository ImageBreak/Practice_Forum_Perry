package net.perry.forum.dao;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;

import net.perry.forum.domain.User;
import net.perry.forum.util.DataSourceUtil;

public class UserDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());
    // 开启驼峰映射
    private BeanProcessor beanProcessor = new GenerousBeanProcessor();
    private RowProcessor processor = new BasicRowProcessor(beanProcessor);

    /**
     * 注册功能
     * @param user
     * @return
     * @throws Exception
     */
    public int save(User user) throws Exception {
        String sql = "insert into user (phone,pwd,sex,img,create_time,role,username) values(?,?,?,?,?,?,?)";
        Object[] params = {
                user.getPhone(),
                user.getPwd(),
                user.getSex(),
                user.getImg(),
                user.getCreateTime(),
                user.getRole(),
                user.getUsername()
        };
        int i;
        try {
            i = queryRunner.update(sql, params);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new Exception();
        }
        return i;
    }

    /**
     * 登录功能
     * @param phone
     * @param md5pwd
     * @return
     */
    public User findByPhoneAndPwd(String phone, String md5pwd) {
        String sql = "select * from user where phone=? and pwd=?";
        User user = null;
        try {
            user = queryRunner.query(sql, new BeanHandler<>(User.class, processor), phone, md5pwd);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return user;
    }

}
