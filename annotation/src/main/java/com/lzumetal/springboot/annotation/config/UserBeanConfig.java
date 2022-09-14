package com.lzumetal.springboot.annotation.config;

import com.lzumetal.springboot.annotation.bean.UserBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaosi
 */
@Configuration
public class UserBeanConfig {



    @Bean
    public UserBean user1() {
        UserBean userBean = new UserBean();
        userBean.setId(1L);
        userBean.setName("张三");
        return userBean;
    }



    @Bean
    public UserBean user2() {
        UserBean userBean = new UserBean();
        userBean.setId(2L);
        userBean.setName("李四");
        return userBean;
    }


}
