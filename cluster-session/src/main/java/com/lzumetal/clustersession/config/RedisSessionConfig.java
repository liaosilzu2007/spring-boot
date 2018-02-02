package com.lzumetal.clustersession.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * <p>Description: </p>
 *
 * @author: liaosi
 * @date: 2018-02-02
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
