package com.lzumetal.springboot.spring.service;


import com.lzumetal.springboot.spring.pojo.Order;

import java.util.List;

/**
 * @author liaosi
 * @date 2020-07-24
 */
public interface NotifyStrategy {

    List<Notify> selectMsgNotify(Order order);

}
