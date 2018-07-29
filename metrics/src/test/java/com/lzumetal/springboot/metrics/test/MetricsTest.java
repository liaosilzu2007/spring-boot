package com.lzumetal.springboot.metrics.test;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.lzumetal.springboot.metrics.Bootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: </p>
 *
 * @author liaosi
 * @date 2018-07-28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bootstrap.class) //classes指定项目启动类
public class MetricsTest {

    private static Queue<Integer> queue = new LinkedBlockingDeque<Integer>();

    private static final Logger log = LoggerFactory.getLogger(MetricsTest.class);


    @Autowired
    private MetricRegistry registry;


    @Test
    public void GaugeTest() throws InterruptedException {
        //让监控的数据输出到控制台
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(3L, TimeUnit.SECONDS);

        registry.register(MetricRegistry.name(MetricsTest.class, "queue", "size"), (Gauge) () -> {
            return queue.size();
        });

        TimeUnit.SECONDS.sleep(5L);

        for (int i = 0; i < 20; i++) {
            queue.add(new Random().nextInt(100));
            TimeUnit.SECONDS.sleep(1L);
        }


    }

    @Test
    public void GaugeTest2() throws InterruptedException {


        registry.register(MetricRegistry.name(MetricsTest.class, "queue", "size"), (Gauge) () -> {
            return queue.size();
        });

        //让监控的数据输出到slf4j的日志
        Slf4jReporter.Builder builder = Slf4jReporter.forRegistry(registry);
        builder.outputTo(log);
        builder.withLoggingLevel(Slf4jReporter.LoggingLevel.ERROR);
        builder.build().start(3, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(5L);

        for (int i = 0; i < 20; i++) {
            queue.add(new Random().nextInt(100));
            TimeUnit.SECONDS.sleep(1L);
        }
    }

}
