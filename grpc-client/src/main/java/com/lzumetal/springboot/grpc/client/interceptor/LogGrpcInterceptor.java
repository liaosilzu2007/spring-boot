package com.lzumetal.springboot.grpc.client.interceptor;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;

/**
 * gRPC 客户端日志拦截器，记录每次 RPC 调用的方法名和耗时
 *
 * @author liaosi
 */
@Slf4j
public class LogGrpcInterceptor implements ClientInterceptor {

    public LogGrpcInterceptor() {
        log.info("[gRPC] LogGrpcInterceptor 实例已创建");
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        final String methodName = method.getFullMethodName();
        log.info("[gRPC] 调用开始: method={}", methodName);

        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> listener, Metadata headers) {
                long start = System.nanoTime();
                super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(listener) {
                    @Override
                    public void onClose(Status status, Metadata trailers) {
                        long elapsed = (System.nanoTime() - start) / 1_000_000;
                        if (status.isOk()) {
                            log.info("[gRPC] 调用成功: method={}, 耗时={}ms", methodName, elapsed);
                        } else {
                            log.warn("[gRPC] 调用失败: method={}, 耗时={}ms, status={}", methodName, elapsed, status);
                        }
                        super.onClose(status, trailers);
                    }
                }, headers);
            }
        };
    }
}
