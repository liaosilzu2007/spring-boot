package com.lzumetal.springboot.grpc.protocol;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 *service 定义接口及 RPC 方法
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.57.2)",
    comments = "Source: BookService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BookServiceGrpc {

  private BookServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "BookService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest,
      com.lzumetal.springboot.grpc.protocol.BookResponse> getGetByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetById",
      requestType = com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest.class,
      responseType = com.lzumetal.springboot.grpc.protocol.BookResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest,
      com.lzumetal.springboot.grpc.protocol.BookResponse> getGetByIdMethod() {
    io.grpc.MethodDescriptor<com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest, com.lzumetal.springboot.grpc.protocol.BookResponse> getGetByIdMethod;
    if ((getGetByIdMethod = BookServiceGrpc.getGetByIdMethod) == null) {
      synchronized (BookServiceGrpc.class) {
        if ((getGetByIdMethod = BookServiceGrpc.getGetByIdMethod) == null) {
          BookServiceGrpc.getGetByIdMethod = getGetByIdMethod =
              io.grpc.MethodDescriptor.<com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest, com.lzumetal.springboot.grpc.protocol.BookResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.lzumetal.springboot.grpc.protocol.BookResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookServiceMethodDescriptorSupplier("GetById"))
              .build();
        }
      }
    }
    return getGetByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.lzumetal.springboot.grpc.protocol.BookAddRequest,
      com.lzumetal.springboot.grpc.protocol.CommonResponse> getAddMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Add",
      requestType = com.lzumetal.springboot.grpc.protocol.BookAddRequest.class,
      responseType = com.lzumetal.springboot.grpc.protocol.CommonResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.lzumetal.springboot.grpc.protocol.BookAddRequest,
      com.lzumetal.springboot.grpc.protocol.CommonResponse> getAddMethod() {
    io.grpc.MethodDescriptor<com.lzumetal.springboot.grpc.protocol.BookAddRequest, com.lzumetal.springboot.grpc.protocol.CommonResponse> getAddMethod;
    if ((getAddMethod = BookServiceGrpc.getAddMethod) == null) {
      synchronized (BookServiceGrpc.class) {
        if ((getAddMethod = BookServiceGrpc.getAddMethod) == null) {
          BookServiceGrpc.getAddMethod = getAddMethod =
              io.grpc.MethodDescriptor.<com.lzumetal.springboot.grpc.protocol.BookAddRequest, com.lzumetal.springboot.grpc.protocol.CommonResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Add"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.lzumetal.springboot.grpc.protocol.BookAddRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.lzumetal.springboot.grpc.protocol.CommonResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookServiceMethodDescriptorSupplier("Add"))
              .build();
        }
      }
    }
    return getAddMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.lzumetal.springboot.grpc.protocol.BookListResponse> getListAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listAll",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.lzumetal.springboot.grpc.protocol.BookListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.lzumetal.springboot.grpc.protocol.BookListResponse> getListAllMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.lzumetal.springboot.grpc.protocol.BookListResponse> getListAllMethod;
    if ((getListAllMethod = BookServiceGrpc.getListAllMethod) == null) {
      synchronized (BookServiceGrpc.class) {
        if ((getListAllMethod = BookServiceGrpc.getListAllMethod) == null) {
          BookServiceGrpc.getListAllMethod = getListAllMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.lzumetal.springboot.grpc.protocol.BookListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.lzumetal.springboot.grpc.protocol.BookListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookServiceMethodDescriptorSupplier("listAll"))
              .build();
        }
      }
    }
    return getListAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.lzumetal.springboot.grpc.protocol.BookListByIdsRequest,
      com.lzumetal.springboot.grpc.protocol.BookResponse> getListByIdsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listByIds",
      requestType = com.lzumetal.springboot.grpc.protocol.BookListByIdsRequest.class,
      responseType = com.lzumetal.springboot.grpc.protocol.BookResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.lzumetal.springboot.grpc.protocol.BookListByIdsRequest,
      com.lzumetal.springboot.grpc.protocol.BookResponse> getListByIdsMethod() {
    io.grpc.MethodDescriptor<com.lzumetal.springboot.grpc.protocol.BookListByIdsRequest, com.lzumetal.springboot.grpc.protocol.BookResponse> getListByIdsMethod;
    if ((getListByIdsMethod = BookServiceGrpc.getListByIdsMethod) == null) {
      synchronized (BookServiceGrpc.class) {
        if ((getListByIdsMethod = BookServiceGrpc.getListByIdsMethod) == null) {
          BookServiceGrpc.getListByIdsMethod = getListByIdsMethod =
              io.grpc.MethodDescriptor.<com.lzumetal.springboot.grpc.protocol.BookListByIdsRequest, com.lzumetal.springboot.grpc.protocol.BookResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listByIds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.lzumetal.springboot.grpc.protocol.BookListByIdsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.lzumetal.springboot.grpc.protocol.BookResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookServiceMethodDescriptorSupplier("listByIds"))
              .build();
        }
      }
    }
    return getListByIdsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BookServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookServiceStub>() {
        @java.lang.Override
        public BookServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookServiceStub(channel, callOptions);
        }
      };
    return BookServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BookServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookServiceBlockingStub>() {
        @java.lang.Override
        public BookServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookServiceBlockingStub(channel, callOptions);
        }
      };
    return BookServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BookServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookServiceFutureStub>() {
        @java.lang.Override
        public BookServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookServiceFutureStub(channel, callOptions);
        }
      };
    return BookServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *service 定义接口及 RPC 方法
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void getById(com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest request,
        io.grpc.stub.StreamObserver<com.lzumetal.springboot.grpc.protocol.BookResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetByIdMethod(), responseObserver);
    }

    /**
     */
    default void add(com.lzumetal.springboot.grpc.protocol.BookAddRequest request,
        io.grpc.stub.StreamObserver<com.lzumetal.springboot.grpc.protocol.CommonResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddMethod(), responseObserver);
    }

    /**
     * <pre>
     *在 proto3 中，RPC 方法的请求参数不能为空 ()，必须指定一个消息类型。
     *使用标准库中的 Empty 类型，是 gRPC 中表示"无请求参数"的标准做法。
     * </pre>
     */
    default void listAll(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.lzumetal.springboot.grpc.protocol.BookListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListAllMethod(), responseObserver);
    }

    /**
     * <pre>
     *proto3 中要表达"多条"，有两种方式：
     *1. 用 repeated 字段包装，比如 BookListResponse。
     *2. 使用 stream 关键字，表示"多条"BookResponse，逐条推送。
     * </pre>
     */
    default void listByIds(com.lzumetal.springboot.grpc.protocol.BookListByIdsRequest request,
        io.grpc.stub.StreamObserver<com.lzumetal.springboot.grpc.protocol.BookResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListByIdsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BookService.
   * <pre>
   *service 定义接口及 RPC 方法
   * </pre>
   */
  public static abstract class BookServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BookServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BookService.
   * <pre>
   *service 定义接口及 RPC 方法
   * </pre>
   */
  public static final class BookServiceStub
      extends io.grpc.stub.AbstractAsyncStub<BookServiceStub> {
    private BookServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookServiceStub(channel, callOptions);
    }

    /**
     */
    public void getById(com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest request,
        io.grpc.stub.StreamObserver<com.lzumetal.springboot.grpc.protocol.BookResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void add(com.lzumetal.springboot.grpc.protocol.BookAddRequest request,
        io.grpc.stub.StreamObserver<com.lzumetal.springboot.grpc.protocol.CommonResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *在 proto3 中，RPC 方法的请求参数不能为空 ()，必须指定一个消息类型。
     *使用标准库中的 Empty 类型，是 gRPC 中表示"无请求参数"的标准做法。
     * </pre>
     */
    public void listAll(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.lzumetal.springboot.grpc.protocol.BookListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *proto3 中要表达"多条"，有两种方式：
     *1. 用 repeated 字段包装，比如 BookListResponse。
     *2. 使用 stream 关键字，表示"多条"BookResponse，逐条推送。
     * </pre>
     */
    public void listByIds(com.lzumetal.springboot.grpc.protocol.BookListByIdsRequest request,
        io.grpc.stub.StreamObserver<com.lzumetal.springboot.grpc.protocol.BookResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListByIdsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BookService.
   * <pre>
   *service 定义接口及 RPC 方法
   * </pre>
   */
  public static final class BookServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BookServiceBlockingStub> {
    private BookServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.lzumetal.springboot.grpc.protocol.BookResponse getById(com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.lzumetal.springboot.grpc.protocol.CommonResponse add(com.lzumetal.springboot.grpc.protocol.BookAddRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *在 proto3 中，RPC 方法的请求参数不能为空 ()，必须指定一个消息类型。
     *使用标准库中的 Empty 类型，是 gRPC 中表示"无请求参数"的标准做法。
     * </pre>
     */
    public com.lzumetal.springboot.grpc.protocol.BookListResponse listAll(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *proto3 中要表达"多条"，有两种方式：
     *1. 用 repeated 字段包装，比如 BookListResponse。
     *2. 使用 stream 关键字，表示"多条"BookResponse，逐条推送。
     * </pre>
     */
    public java.util.Iterator<com.lzumetal.springboot.grpc.protocol.BookResponse> listByIds(
        com.lzumetal.springboot.grpc.protocol.BookListByIdsRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListByIdsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BookService.
   * <pre>
   *service 定义接口及 RPC 方法
   * </pre>
   */
  public static final class BookServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<BookServiceFutureStub> {
    private BookServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.lzumetal.springboot.grpc.protocol.BookResponse> getById(
        com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.lzumetal.springboot.grpc.protocol.CommonResponse> add(
        com.lzumetal.springboot.grpc.protocol.BookAddRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *在 proto3 中，RPC 方法的请求参数不能为空 ()，必须指定一个消息类型。
     *使用标准库中的 Empty 类型，是 gRPC 中表示"无请求参数"的标准做法。
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.lzumetal.springboot.grpc.protocol.BookListResponse> listAll(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListAllMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BY_ID = 0;
  private static final int METHODID_ADD = 1;
  private static final int METHODID_LIST_ALL = 2;
  private static final int METHODID_LIST_BY_IDS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BY_ID:
          serviceImpl.getById((com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest) request,
              (io.grpc.stub.StreamObserver<com.lzumetal.springboot.grpc.protocol.BookResponse>) responseObserver);
          break;
        case METHODID_ADD:
          serviceImpl.add((com.lzumetal.springboot.grpc.protocol.BookAddRequest) request,
              (io.grpc.stub.StreamObserver<com.lzumetal.springboot.grpc.protocol.CommonResponse>) responseObserver);
          break;
        case METHODID_LIST_ALL:
          serviceImpl.listAll((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.lzumetal.springboot.grpc.protocol.BookListResponse>) responseObserver);
          break;
        case METHODID_LIST_BY_IDS:
          serviceImpl.listByIds((com.lzumetal.springboot.grpc.protocol.BookListByIdsRequest) request,
              (io.grpc.stub.StreamObserver<com.lzumetal.springboot.grpc.protocol.BookResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest,
              com.lzumetal.springboot.grpc.protocol.BookResponse>(
                service, METHODID_GET_BY_ID)))
        .addMethod(
          getAddMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.lzumetal.springboot.grpc.protocol.BookAddRequest,
              com.lzumetal.springboot.grpc.protocol.CommonResponse>(
                service, METHODID_ADD)))
        .addMethod(
          getListAllMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.lzumetal.springboot.grpc.protocol.BookListResponse>(
                service, METHODID_LIST_ALL)))
        .addMethod(
          getListByIdsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.lzumetal.springboot.grpc.protocol.BookListByIdsRequest,
              com.lzumetal.springboot.grpc.protocol.BookResponse>(
                service, METHODID_LIST_BY_IDS)))
        .build();
  }

  private static abstract class BookServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BookServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.lzumetal.springboot.grpc.protocol.BookServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BookService");
    }
  }

  private static final class BookServiceFileDescriptorSupplier
      extends BookServiceBaseDescriptorSupplier {
    BookServiceFileDescriptorSupplier() {}
  }

  private static final class BookServiceMethodDescriptorSupplier
      extends BookServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    BookServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BookServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BookServiceFileDescriptorSupplier())
              .addMethod(getGetByIdMethod())
              .addMethod(getAddMethod())
              .addMethod(getListAllMethod())
              .addMethod(getListByIdsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
