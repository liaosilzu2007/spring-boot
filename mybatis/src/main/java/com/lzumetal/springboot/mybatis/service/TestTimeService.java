package com.lzumetal.springboot.mybatis.service;

import com.lzumetal.springboot.mybatis.entity.TestTime;
import com.lzumetal.springboot.mybatis.mapper.TestTimeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liaosi
 * @date 2022-01-25
 */
@Slf4j
@Service
public class TestTimeService {

    @Autowired
    private TestTimeMapper testTimeMapper;

    public TestTime getById(Integer id) {
        return testTimeMapper.getById(id);
    }

    public void addOne() {
        Date now = new Date();
        TestTime entity = new TestTime();
        entity.setCreateTimestamp(now);
        entity.setCreateDateTime(now);
        log.info("插入数据|{}", entity);
        testTimeMapper.insert(entity);
    }
}
