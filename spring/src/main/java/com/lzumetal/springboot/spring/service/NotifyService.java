package com.lzumetal.springboot.spring.service;

import com.lzumetal.springboot.spring.pojo.Order;
import com.lzumetal.springboot.spring.util.SpringContextUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaosi
 * @date 2020-07-24
 */
@Service
public class NotifyService {


    void createOrderSuccNotify(Order order, Class<? extends NotifyStrategy> strategyCalzz) {
        NotifyStrategy strategy = SpringContextUtil.getBean(strategyCalzz);
        List<Notify> notifies = strategy.selectMsgNotify(order);
        for (Notify notify : notifies) {
            notify.sendNotify();
        }
    }


}
