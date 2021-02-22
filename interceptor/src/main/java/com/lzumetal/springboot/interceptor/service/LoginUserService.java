package com.lzumetal.springboot.interceptor.service;

import com.lzumetal.springboot.interceptor.entity.LoginUser;
import org.springframework.stereotype.Service;

/**
 * @author liaosi
 * @date 2021-02-20
 */
@Service
public class LoginUserService {


    public LoginUser findById(long id) {
        if (id <= 0) {
            return null;
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setId(id);
        loginUser.setLoginName("zhangsan@123.com");
        loginUser.setNickname("zhangsan");
        return loginUser;
    }

}
