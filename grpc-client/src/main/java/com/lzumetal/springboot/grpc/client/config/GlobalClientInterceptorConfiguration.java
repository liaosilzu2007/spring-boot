package com.lzumetal.springboot.grpc.client.config;

import com.lzumetal.springboot.grpc.client.interceptor.LogGrpcInterceptor;
import io.grpc.ClientInterceptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaosi
 */
@Configuration
public class GlobalClientInterceptorConfiguration {


    @Bean
    @GrpcGlobalClientInterceptor
    public ClientInterceptor logClientInterceptor() {
        return new LogGrpcInterceptor();
    }

}
