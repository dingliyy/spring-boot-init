package com.aq360.auth.manager.rpc.authentication;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * 认证
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: JWTResp.proto")
public final class AuthenticationServiceGrpc {

  private AuthenticationServiceGrpc() {}

  public static final String SERVICE_NAME = "AuthenticationService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest,
      com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse> METHOD_VALIDATE_TOKEN =
      io.grpc.MethodDescriptor.<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest, com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "AuthenticationService", "validateToken"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthenticationServiceStub newStub(io.grpc.Channel channel) {
    return new AuthenticationServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthenticationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AuthenticationServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthenticationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AuthenticationServiceFutureStub(channel);
  }

  /**
   * <pre>
   * 认证
   * </pre>
   */
  public static abstract class AuthenticationServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *校验token
     * </pre>
     */
    public void validateToken(com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest request,
        io.grpc.stub.StreamObserver<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_VALIDATE_TOKEN, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_VALIDATE_TOKEN,
            asyncUnaryCall(
              new MethodHandlers<
                com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest,
                com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse>(
                  this, METHODID_VALIDATE_TOKEN)))
          .build();
    }
  }

  /**
   * <pre>
   * 认证
   * </pre>
   */
  public static final class AuthenticationServiceStub extends io.grpc.stub.AbstractStub<AuthenticationServiceStub> {
    private AuthenticationServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthenticationServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticationServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthenticationServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *校验token
     * </pre>
     */
    public void validateToken(com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest request,
        io.grpc.stub.StreamObserver<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_VALIDATE_TOKEN, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * 认证
   * </pre>
   */
  public static final class AuthenticationServiceBlockingStub extends io.grpc.stub.AbstractStub<AuthenticationServiceBlockingStub> {
    private AuthenticationServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthenticationServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticationServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthenticationServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *校验token
     * </pre>
     */
    public com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse validateToken(com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_VALIDATE_TOKEN, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * 认证
   * </pre>
   */
  public static final class AuthenticationServiceFutureStub extends io.grpc.stub.AbstractStub<AuthenticationServiceFutureStub> {
    private AuthenticationServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthenticationServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticationServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthenticationServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *校验token
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse> validateToken(
        com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_VALIDATE_TOKEN, getCallOptions()), request);
    }
  }

  private static final int METHODID_VALIDATE_TOKEN = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AuthenticationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AuthenticationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_VALIDATE_TOKEN:
          serviceImpl.validateToken((com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest) request,
              (io.grpc.stub.StreamObserver<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse>) responseObserver);
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

  private static final class AuthenticationServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.aq360.auth.manager.rpc.authentication.AuthenticationProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AuthenticationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthenticationServiceDescriptorSupplier())
              .addMethod(METHOD_VALIDATE_TOKEN)
              .build();
        }
      }
    }
    return result;
  }
}
