package com.lzumetal.springboot.delaytask.service.impl;

import com.lzumetal.springboot.delaytask.enums.ETaskInfo;
import com.lzumetal.springboot.delaytask.service.DelayTaskService;
import com.lzumetal.springboot.delaytask.service.TimeoutCallable;
import com.lzumetal.springboot.utils.common.ServiceException;
import com.lzumetal.springboot.utils.response.EBaseResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2021-11-18
 */
@Service
public class DelayTaskServiceImpl implements DelayTaskService {

    @Autowired
    private StringRedisTemplate redisTemplate;


    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<? extends TimeoutCallable> callable = PayTimeoutCallable.class;
        TimeoutCallable instance = callable.newInstance();
        instance.call("123");

    }


    public void dealDelay(ETaskInfo eTaskInfo) throws IllegalAccessException, InstantiationException {
        Objects.requireNonNull(eTaskInfo);
        BoundZSetOperations<String, String> ops = redisTemplate.boundZSetOps(eTaskInfo.getKey());
        long currentMillis = System.currentTimeMillis();
        Set<String> members = ops.rangeByScore(0, currentMillis);
        if (!CollectionUtils.isEmpty(members)) {
            for (String member : members) {
                Long remove = ops.remove(member);
                if (remove != null && remove > 0) {
                    //remove大于0表示移除成功
                    Class<? extends TimeoutCallable> callable = eTaskInfo.getCallable();
                    TimeoutCallable instance = callable.newInstance();
                    instance.call(member);
                }
            }
        }
    }


    @Override
    public boolean commit(String taskType, String bizId) {
        ETaskInfo eTaskInfo = ETaskInfo.getByKey(taskType);
        if (eTaskInfo == null) {
            throw new ServiceException(EBaseResponseCode.C401);
        }
        long score = System.currentTimeMillis() + 5 * 60 * 1000;
        BoundZSetOperations<String, String> ops = redisTemplate.boundZSetOps(eTaskInfo.getKey());
        ops.add(bizId, score);
        ops.expire(1, TimeUnit.HOURS);
        return true;
    }
}
