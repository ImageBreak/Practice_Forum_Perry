package net.perry.forum.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import net.perry.forum.domain.User;
import net.perry.forum.service.UserService;
import net.perry.forum.service.impl.UserServiceImpl;

@WebServlet(name = "userServlet", urlPatterns = { "/user" })
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    public void register(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User();
        Map<String, String[]> map = req.getParameterMap();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int i = userService.register(user);

        if(i > 0){
            //注册成功，跳转到登陆界面 TODO
        }else{
            //注册失败， 跳转到失败页面 TODO
        }
    }
}
