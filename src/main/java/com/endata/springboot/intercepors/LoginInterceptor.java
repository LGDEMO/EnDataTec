package com.endata.springboot.intercepors;

import com.endata.springboot.model.User;
import com.endata.springboot.service.UserService;
import com.endata.springboot.util.JWTUtil;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ligang
 * @create 2019-11-23 16:05
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Autowired
    private UserService userService;


    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        } else {
            //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
            HttpSession session = request.getSession();
            //这里的User是登陆时放入session的
            response.setContentType("text/html;charset=UTF-8");
            // 告诉服务器 请求的内容是什么编码格式
            request.setCharacterEncoding("UTF-8");
            Cookie[] cookies = request.getCookies();
            String userName = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String token = cookie.getValue();
                    String s = JWTUtil.getUsername(token);
                    if (s != null) {
                        userName = s;
                    }
                }
            }
            User user = userService.getDataByUserName(userName);

            //如果session中没有user，表示没登陆
            if (user == null) {
                //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
                //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
                return false;
            } else {
                return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
            }
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

}
