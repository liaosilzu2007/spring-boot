package com.lzumetal.springboot.aop.dubbo;

import com.lzumetal.springboot.aop.entity.Order;
import com.lzumetal.springboot.aop.exception.OrderServiceException;
import com.lzumetal.springboot.aop.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 调用订单dubbo服务
 *
 * @author liaosi
 * @date 2020-07-07
 */
@Service
@Slf4j
public class OrderInvoker {



    public Order queryById(Long id) throws ServiceException {
        log.info("模拟调用dubbo服务...");
        throw new OrderServiceException("订单不存在");
    }


}
