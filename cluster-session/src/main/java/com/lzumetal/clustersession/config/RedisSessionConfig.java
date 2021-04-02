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
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 300) //maxInactiveIntervalInSeconds用于设置 session 过期时间
public class RedisSessionConfig {


}
