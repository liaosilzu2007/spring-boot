package com.lzumetal.springboot.demodatabase.controller;

import com.lzumetal.springboot.demodatabase.entity.TestTime;
import com.lzumetal.springboot.demodatabase.service.TestTimeService;
import com.lzumetal.springboot.utils.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaosi
 * @date 2022-01-25
 */
@Slf4j
@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TestTimeService testTimeService;


    @RequestMapping("/getById")
    public ResponseData getById(Integer id) {
        TestTime testTime = testTimeService.getById(id);
        log.info("根据id查询|{}", testTime);
        return ResponseData.data(testTime);
    }


    @RequestMapping("/addOne")
    public ResponseData addOne() {
        testTimeService.addOne();
        return ResponseData.success();
    }
}
