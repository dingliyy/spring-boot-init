package com.aq360.auth.manager.rpc.authentication;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 * <pre>
 * 认证
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 0.14.1)",
    comments = "Source: JWTResp.proto")
public class AuthenticationServiceGrpc {

  private AuthenticationServiceGrpc() {}

  public static final String SERVICE_NAME = "AuthenticationService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest,
      com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse> METHOD_VALIDATE_TOKEN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "AuthenticationService", "validateToken"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse.getDefaultInstance()));

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
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
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
  public static interface AuthenticationService {

    /**
     * <pre>
     *校验token
     * </pre>
     */
    public void validateToken(com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest request,
        io.grpc.stub.StreamObserver<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractAuthenticationService implements AuthenticationService, io.grpc.BindableService {

    @java.lang.Override
    public void validateToken(com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest request,
        io.grpc.stub.StreamObserver<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_VALIDATE_TOKEN, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return AuthenticationServiceGrpc.bindService(this);
    }
  }

  /**
   * <pre>
   * 认证
   * </pre>
   */
  public static interface AuthenticationServiceBlockingClient {

    /**
     * <pre>
     *校验token
     * </pre>
     */
    public com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse validateToken(com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest request);
  }

  /**
   * <pre>
   * 认证
   * </pre>
   */
  public static interface AuthenticationServiceFutureClient {

    /**
     * <pre>
     *校验token
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse> validateToken(
        com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest request);
  }

  public static class AuthenticationServiceStub extends io.grpc.stub.AbstractStub<AuthenticationServiceStub>
      implements AuthenticationService {
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

    @java.lang.Override
    public void validateToken(com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest request,
        io.grpc.stub.StreamObserver<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_VALIDATE_TOKEN, getCallOptions()), request, responseObserver);
    }
  }

  public static class AuthenticationServiceBlockingStub extends io.grpc.stub.AbstractStub<AuthenticationServiceBlockingStub>
      implements AuthenticationServiceBlockingClient {
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

    @java.lang.Override
    public com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse validateToken(com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_VALIDATE_TOKEN, getCallOptions(), request);
    }
  }

  public static class AuthenticationServiceFutureStub extends io.grpc.stub.AbstractStub<AuthenticationServiceFutureStub>
      implements AuthenticationServiceFutureClient {
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

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse> validateToken(
        com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_VALIDATE_TOKEN, getCallOptions()), request);
    }
  }

  private static final int METHODID_VALIDATE_TOKEN = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AuthenticationService serviceImpl;
    private final int methodId;

    public MethodHandlers(AuthenticationService serviceImpl, int methodId) {
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

  public static io.grpc.ServerServiceDefinition bindService(
      final AuthenticationService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_VALIDATE_TOKEN,
          asyncUnaryCall(
            new MethodHandlers<
              com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest,
              com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse>(
                serviceImpl, METHODID_VALIDATE_TOKEN)))
        .build();
  }
}
