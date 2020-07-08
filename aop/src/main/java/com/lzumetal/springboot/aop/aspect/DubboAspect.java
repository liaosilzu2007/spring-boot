package com.lzumetal.springboot.aop.aspect;

import com.lzumetal.springboot.aop.exception.OrderServiceException;
import com.lzumetal.springboot.aop.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author liaosi
 * @date 2020-07-07
 */
@Aspect
@Component
@Slf4j
public class DubboAspect {


    @Around(value = "execution(public * com.lzumetal.springboot.aop.dubbo.OrderInvoker.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws ServiceException {
        long start = System.currentTimeMillis();
        String name = joinPoint.getSignature().getName();
        Object proceed;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            String errMsg = "服务器错误";
            if (e instanceof OrderServiceException) {
                errMsg = e.getMessage();
            }
            throw new ServiceException(500, errMsg);
        } finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            log.info("dubbo服务调用耗时|method={}|{}ms", name, time);
        }
        return proceed;
    }


}
