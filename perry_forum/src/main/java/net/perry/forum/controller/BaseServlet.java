package net.perry.forum.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {

    /**
     * 子类的Servlet被访问，会调用service方法，子类没有重写，就会调用父类
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // 获取请求方法
        String method = req.getParameter("method");
        if (method != null) {
            try {
                // 获得当前被访问对象的字节码对象，和字节码对象里指定的方法
                Method targetMethod = this.getClass().getMethod(method, HttpServletRequest.class,
                        HttpServletResponse.class);
                // 执行指定的方法
                targetMethod.invoke(this, req, resp);

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

}
