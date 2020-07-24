package com.lzumetal.springboot.spring.service;

import com.lzumetal.springboot.spring.pojo.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaosi
 * @date 2020-07-24
 */
@Service
public class DefaultNotifyStrategy implements NotifyStrategy {


    @Override
    public List<Notify> selectMsgNotify(Order order) {
        return null;
    }


}
