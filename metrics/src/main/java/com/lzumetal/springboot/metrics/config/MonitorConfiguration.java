package com.lzumetal.springboot.metrics.config;

import com.codahale.metrics.MetricRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: </p>
 *
 * @author liaosi
 * @date 2018-07-28
 */
@Configuration
public class MonitorConfiguration {


    /**
     * 其实就是一个metrics容器，因为该类的一个属性final ConcurrentMap<String, Metric> metrics，
     * 在实际使用中做成单例的
     * @return
     */
    @Bean(name = "metricRegistry")
    public MetricRegistry metricRegistry() {
        return new MetricRegistry();
    }


}
