package net.perry.forum.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import net.perry.forum.dao.UserDao;
import net.perry.forum.domain.User;
import net.perry.forum.service.UserService;
import net.perry.forum.util.CommonUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDao();

    @Override
    public int register(User user) {

        user.setRole(1);
        user.setCreateTime(LocalDateTime.now());
        user.setImg(getRandomImg());

        user.setPwd(CommonUtil.MD5(user.getPwd()));

        try {
            return userDao.save(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    private static final String [] headImg = {
        "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
        "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
        "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
        "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
        "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRandomImg(){
        int size = headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }

    @Override
    public User login(String phone, String pwd) {
        
        String md5pwd = CommonUtil.MD5(pwd);
        User user = userDao.findByPhoneAndPwd(phone,md5pwd);
        return user;
    }
}
