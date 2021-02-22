package com.lzumetal.springboot.interceptor.interceptor;

import com.lzumetal.springboot.interceptor.annotation.NotRequireLogin;
import com.lzumetal.springboot.interceptor.config.Constants;
import com.lzumetal.springboot.interceptor.entity.LoginUser;
import com.lzumetal.springboot.interceptor.service.LoginUserService;
import com.lzumetal.springboot.utils.TokenUtils;
import com.lzumetal.springboot.utils.common.ServiceException;
import com.lzumetal.springboot.utils.response.EBaseResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 用户登陆拦截器
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginUserService loginUserService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证用户登陆 todo
        log.info("开始验证用户登陆...");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        NotRequireLogin notRequireLogin = method.getAnnotation(NotRequireLogin.class);
        if (notRequireLogin != null) {
            return true;
        }
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            throw new ServiceException(EBaseResponseCode.C500.getCode(), "无效的token");
        }
        long id = TokenUtils.parseJwtTokenToId(token);
        LoginUser loginUser = loginUserService.findById(id);
        if (loginUser == null) {
            throw new ServiceException(EBaseResponseCode.C500.getCode(), "无效的token");
        }
        request.setAttribute(Constants.CURRENT_USER, loginUser);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("用户登陆拦截器...afterCompletion方法执行...");
    }

}
