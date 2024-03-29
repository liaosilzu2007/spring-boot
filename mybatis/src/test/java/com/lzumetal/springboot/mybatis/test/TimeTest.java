package com.lzumetal.springboot.mybatis.test;

import com.lzumetal.springboot.mybatis.StartupApplication;
import com.lzumetal.springboot.mybatis.entity.TestTime;
import com.lzumetal.springboot.mybatis.service.TestTimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplication.class)
public class TimeTest {


    @Autowired
    private TestTimeService testTimeService;



    @Test
    public void testGetById() {
        TestTime testTime = testTimeService.getById(1);
        System.out.println(testTime);
    }


}
