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

    /**
     * 登录功能
     * @param req
     * @param resp
     */
    public void login(HttpServletRequest req, HttpServletResponse resp){
        String phone = req.getParameter("phone");
        String pwd = req.getParameter("pwd");

        User user = userService.login(phone,pwd);

        if(user != null){
            req.getSession().setAttribute("loginUser", user);
        }else{
            req.setAttribute("msg","用户名或者密码不正确");
        }
    }

    /**
     * 退出登录
     * @param req
     * @param resp
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp){
        req.getSession().invalidate();
        //页面跳转TODO
    }

    /**
     * 注册功能
     * @param req
     * @param resp
     */
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
