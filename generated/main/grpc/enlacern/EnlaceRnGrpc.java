package enlacern;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: EnlaceRn.proto")
public final class EnlaceRnGrpc {

  private EnlaceRnGrpc() {}

  public static final String SERVICE_NAME = "enlacern.EnlaceRn";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.usuarioRequest,
      enlacern.EnlaceRnOuterClass.usuarioResponse> getAutentificacionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "autentificacion",
      requestType = enlacern.EnlaceRnOuterClass.usuarioRequest.class,
      responseType = enlacern.EnlaceRnOuterClass.usuarioResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.usuarioRequest,
      enlacern.EnlaceRnOuterClass.usuarioResponse> getAutentificacionMethod() {
    io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.usuarioRequest, enlacern.EnlaceRnOuterClass.usuarioResponse> getAutentificacionMethod;
    if ((getAutentificacionMethod = EnlaceRnGrpc.getAutentificacionMethod) == null) {
      synchronized (EnlaceRnGrpc.class) {
        if ((getAutentificacionMethod = EnlaceRnGrpc.getAutentificacionMethod) == null) {
          EnlaceRnGrpc.getAutentificacionMethod = getAutentificacionMethod =
              io.grpc.MethodDescriptor.<enlacern.EnlaceRnOuterClass.usuarioRequest, enlacern.EnlaceRnOuterClass.usuarioResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "autentificacion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  enlacern.EnlaceRnOuterClass.usuarioRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  enlacern.EnlaceRnOuterClass.usuarioResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EnlaceRnMethodDescriptorSupplier("autentificacion"))
              .build();
        }
      }
    }
    return getAutentificacionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.EnlaceRequest,
      enlacern.EnlaceRnOuterClass.EnlaceResponse> getRegistrarEnlaceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "registrarEnlace",
      requestType = enlacern.EnlaceRnOuterClass.EnlaceRequest.class,
      responseType = enlacern.EnlaceRnOuterClass.EnlaceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.EnlaceRequest,
      enlacern.EnlaceRnOuterClass.EnlaceResponse> getRegistrarEnlaceMethod() {
    io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.EnlaceRequest, enlacern.EnlaceRnOuterClass.EnlaceResponse> getRegistrarEnlaceMethod;
    if ((getRegistrarEnlaceMethod = EnlaceRnGrpc.getRegistrarEnlaceMethod) == null) {
      synchronized (EnlaceRnGrpc.class) {
        if ((getRegistrarEnlaceMethod = EnlaceRnGrpc.getRegistrarEnlaceMethod) == null) {
          EnlaceRnGrpc.getRegistrarEnlaceMethod = getRegistrarEnlaceMethod =
              io.grpc.MethodDescriptor.<enlacern.EnlaceRnOuterClass.EnlaceRequest, enlacern.EnlaceRnOuterClass.EnlaceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "registrarEnlace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  enlacern.EnlaceRnOuterClass.EnlaceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  enlacern.EnlaceRnOuterClass.EnlaceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EnlaceRnMethodDescriptorSupplier("registrarEnlace"))
              .build();
        }
      }
    }
    return getRegistrarEnlaceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.clientesRequest,
      enlacern.EnlaceRnOuterClass.EnlaceResponse> getGetEnlaceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getEnlace",
      requestType = enlacern.EnlaceRnOuterClass.clientesRequest.class,
      responseType = enlacern.EnlaceRnOuterClass.EnlaceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.clientesRequest,
      enlacern.EnlaceRnOuterClass.EnlaceResponse> getGetEnlaceMethod() {
    io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.clientesRequest, enlacern.EnlaceRnOuterClass.EnlaceResponse> getGetEnlaceMethod;
    if ((getGetEnlaceMethod = EnlaceRnGrpc.getGetEnlaceMethod) == null) {
      synchronized (EnlaceRnGrpc.class) {
        if ((getGetEnlaceMethod = EnlaceRnGrpc.getGetEnlaceMethod) == null) {
          EnlaceRnGrpc.getGetEnlaceMethod = getGetEnlaceMethod =
              io.grpc.MethodDescriptor.<enlacern.EnlaceRnOuterClass.clientesRequest, enlacern.EnlaceRnOuterClass.EnlaceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getEnlace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  enlacern.EnlaceRnOuterClass.clientesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  enlacern.EnlaceRnOuterClass.EnlaceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EnlaceRnMethodDescriptorSupplier("getEnlace"))
              .build();
        }
      }
    }
    return getGetEnlaceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.enlace,
      enlacern.EnlaceRnOuterClass.ListaEnlace> getGetEnlacesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getEnlaces",
      requestType = enlacern.EnlaceRnOuterClass.enlace.class,
      responseType = enlacern.EnlaceRnOuterClass.ListaEnlace.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.enlace,
      enlacern.EnlaceRnOuterClass.ListaEnlace> getGetEnlacesMethod() {
    io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.enlace, enlacern.EnlaceRnOuterClass.ListaEnlace> getGetEnlacesMethod;
    if ((getGetEnlacesMethod = EnlaceRnGrpc.getGetEnlacesMethod) == null) {
      synchronized (EnlaceRnGrpc.class) {
        if ((getGetEnlacesMethod = EnlaceRnGrpc.getGetEnlacesMethod) == null) {
          EnlaceRnGrpc.getGetEnlacesMethod = getGetEnlacesMethod =
              io.grpc.MethodDescriptor.<enlacern.EnlaceRnOuterClass.enlace, enlacern.EnlaceRnOuterClass.ListaEnlace>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getEnlaces"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  enlacern.EnlaceRnOuterClass.enlace.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  enlacern.EnlaceRnOuterClass.ListaEnlace.getDefaultInstance()))
              .setSchemaDescriptor(new EnlaceRnMethodDescriptorSupplier("getEnlaces"))
              .build();
        }
      }
    }
    return getGetEnlacesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.clientesRequest,
      enlacern.EnlaceRnOuterClass.clienteReponse> getGetClientesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getClientes",
      requestType = enlacern.EnlaceRnOuterClass.clientesRequest.class,
      responseType = enlacern.EnlaceRnOuterClass.clienteReponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.clientesRequest,
      enlacern.EnlaceRnOuterClass.clienteReponse> getGetClientesMethod() {
    io.grpc.MethodDescriptor<enlacern.EnlaceRnOuterClass.clientesRequest, enlacern.EnlaceRnOuterClass.clienteReponse> getGetClientesMethod;
    if ((getGetClientesMethod = EnlaceRnGrpc.getGetClientesMethod) == null) {
      synchronized (EnlaceRnGrpc.class) {
        if ((getGetClientesMethod = EnlaceRnGrpc.getGetClientesMethod) == null) {
          EnlaceRnGrpc.getGetClientesMethod = getGetClientesMethod =
              io.grpc.MethodDescriptor.<enlacern.EnlaceRnOuterClass.clientesRequest, enlacern.EnlaceRnOuterClass.clienteReponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getClientes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  enlacern.EnlaceRnOuterClass.clientesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  enlacern.EnlaceRnOuterClass.clienteReponse.getDefaultInstance()))
              .setSchemaDescriptor(new EnlaceRnMethodDescriptorSupplier("getClientes"))
              .build();
        }
      }
    }
    return getGetClientesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnlaceRnStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EnlaceRnStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EnlaceRnStub>() {
        @java.lang.Override
        public EnlaceRnStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EnlaceRnStub(channel, callOptions);
        }
      };
    return EnlaceRnStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnlaceRnBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EnlaceRnBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EnlaceRnBlockingStub>() {
        @java.lang.Override
        public EnlaceRnBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EnlaceRnBlockingStub(channel, callOptions);
        }
      };
    return EnlaceRnBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EnlaceRnFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EnlaceRnFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EnlaceRnFutureStub>() {
        @java.lang.Override
        public EnlaceRnFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EnlaceRnFutureStub(channel, callOptions);
        }
      };
    return EnlaceRnFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class EnlaceRnImplBase implements io.grpc.BindableService {

    /**
     */
    public void autentificacion(enlacern.EnlaceRnOuterClass.usuarioRequest request,
        io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.usuarioResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAutentificacionMethod(), responseObserver);
    }

    /**
     */
    public void registrarEnlace(enlacern.EnlaceRnOuterClass.EnlaceRequest request,
        io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.EnlaceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegistrarEnlaceMethod(), responseObserver);
    }

    /**
     */
    public void getEnlace(enlacern.EnlaceRnOuterClass.clientesRequest request,
        io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.EnlaceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEnlaceMethod(), responseObserver);
    }

    /**
     */
    public void getEnlaces(enlacern.EnlaceRnOuterClass.enlace request,
        io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.ListaEnlace> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetEnlacesMethod(), responseObserver);
    }

    /**
     */
    public void getClientes(enlacern.EnlaceRnOuterClass.clientesRequest request,
        io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.clienteReponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetClientesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAutentificacionMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                enlacern.EnlaceRnOuterClass.usuarioRequest,
                enlacern.EnlaceRnOuterClass.usuarioResponse>(
                  this, METHODID_AUTENTIFICACION)))
          .addMethod(
            getRegistrarEnlaceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                enlacern.EnlaceRnOuterClass.EnlaceRequest,
                enlacern.EnlaceRnOuterClass.EnlaceResponse>(
                  this, METHODID_REGISTRAR_ENLACE)))
          .addMethod(
            getGetEnlaceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                enlacern.EnlaceRnOuterClass.clientesRequest,
                enlacern.EnlaceRnOuterClass.EnlaceResponse>(
                  this, METHODID_GET_ENLACE)))
          .addMethod(
            getGetEnlacesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                enlacern.EnlaceRnOuterClass.enlace,
                enlacern.EnlaceRnOuterClass.ListaEnlace>(
                  this, METHODID_GET_ENLACES)))
          .addMethod(
            getGetClientesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                enlacern.EnlaceRnOuterClass.clientesRequest,
                enlacern.EnlaceRnOuterClass.clienteReponse>(
                  this, METHODID_GET_CLIENTES)))
          .build();
    }
  }

  /**
   */
  public static final class EnlaceRnStub extends io.grpc.stub.AbstractAsyncStub<EnlaceRnStub> {
    private EnlaceRnStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnlaceRnStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EnlaceRnStub(channel, callOptions);
    }

    /**
     */
    public void autentificacion(enlacern.EnlaceRnOuterClass.usuarioRequest request,
        io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.usuarioResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAutentificacionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void registrarEnlace(enlacern.EnlaceRnOuterClass.EnlaceRequest request,
        io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.EnlaceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegistrarEnlaceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEnlace(enlacern.EnlaceRnOuterClass.clientesRequest request,
        io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.EnlaceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEnlaceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEnlaces(enlacern.EnlaceRnOuterClass.enlace request,
        io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.ListaEnlace> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetEnlacesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getClientes(enlacern.EnlaceRnOuterClass.clientesRequest request,
        io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.clienteReponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetClientesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnlaceRnBlockingStub extends io.grpc.stub.AbstractBlockingStub<EnlaceRnBlockingStub> {
    private EnlaceRnBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnlaceRnBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EnlaceRnBlockingStub(channel, callOptions);
    }

    /**
     */
    public enlacern.EnlaceRnOuterClass.usuarioResponse autentificacion(enlacern.EnlaceRnOuterClass.usuarioRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAutentificacionMethod(), getCallOptions(), request);
    }

    /**
     */
    public enlacern.EnlaceRnOuterClass.EnlaceResponse registrarEnlace(enlacern.EnlaceRnOuterClass.EnlaceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegistrarEnlaceMethod(), getCallOptions(), request);
    }

    /**
     */
    public enlacern.EnlaceRnOuterClass.EnlaceResponse getEnlace(enlacern.EnlaceRnOuterClass.clientesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEnlaceMethod(), getCallOptions(), request);
    }

    /**
     */
    public enlacern.EnlaceRnOuterClass.ListaEnlace getEnlaces(enlacern.EnlaceRnOuterClass.enlace request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetEnlacesMethod(), getCallOptions(), request);
    }

    /**
     */
    public enlacern.EnlaceRnOuterClass.clienteReponse getClientes(enlacern.EnlaceRnOuterClass.clientesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetClientesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnlaceRnFutureStub extends io.grpc.stub.AbstractFutureStub<EnlaceRnFutureStub> {
    private EnlaceRnFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnlaceRnFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EnlaceRnFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enlacern.EnlaceRnOuterClass.usuarioResponse> autentificacion(
        enlacern.EnlaceRnOuterClass.usuarioRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAutentificacionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enlacern.EnlaceRnOuterClass.EnlaceResponse> registrarEnlace(
        enlacern.EnlaceRnOuterClass.EnlaceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegistrarEnlaceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enlacern.EnlaceRnOuterClass.EnlaceResponse> getEnlace(
        enlacern.EnlaceRnOuterClass.clientesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEnlaceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enlacern.EnlaceRnOuterClass.ListaEnlace> getEnlaces(
        enlacern.EnlaceRnOuterClass.enlace request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetEnlacesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enlacern.EnlaceRnOuterClass.clienteReponse> getClientes(
        enlacern.EnlaceRnOuterClass.clientesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetClientesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AUTENTIFICACION = 0;
  private static final int METHODID_REGISTRAR_ENLACE = 1;
  private static final int METHODID_GET_ENLACE = 2;
  private static final int METHODID_GET_ENLACES = 3;
  private static final int METHODID_GET_CLIENTES = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnlaceRnImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EnlaceRnImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTENTIFICACION:
          serviceImpl.autentificacion((enlacern.EnlaceRnOuterClass.usuarioRequest) request,
              (io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.usuarioResponse>) responseObserver);
          break;
        case METHODID_REGISTRAR_ENLACE:
          serviceImpl.registrarEnlace((enlacern.EnlaceRnOuterClass.EnlaceRequest) request,
              (io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.EnlaceResponse>) responseObserver);
          break;
        case METHODID_GET_ENLACE:
          serviceImpl.getEnlace((enlacern.EnlaceRnOuterClass.clientesRequest) request,
              (io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.EnlaceResponse>) responseObserver);
          break;
        case METHODID_GET_ENLACES:
          serviceImpl.getEnlaces((enlacern.EnlaceRnOuterClass.enlace) request,
              (io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.ListaEnlace>) responseObserver);
          break;
        case METHODID_GET_CLIENTES:
          serviceImpl.getClientes((enlacern.EnlaceRnOuterClass.clientesRequest) request,
              (io.grpc.stub.StreamObserver<enlacern.EnlaceRnOuterClass.clienteReponse>) responseObserver);
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

  private static abstract class EnlaceRnBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EnlaceRnBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return enlacern.EnlaceRnOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EnlaceRn");
    }
  }

  private static final class EnlaceRnFileDescriptorSupplier
      extends EnlaceRnBaseDescriptorSupplier {
    EnlaceRnFileDescriptorSupplier() {}
  }

  private static final class EnlaceRnMethodDescriptorSupplier
      extends EnlaceRnBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EnlaceRnMethodDescriptorSupplier(String methodName) {
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
      synchronized (EnlaceRnGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EnlaceRnFileDescriptorSupplier())
              .addMethod(getAutentificacionMethod())
              .addMethod(getRegistrarEnlaceMethod())
              .addMethod(getGetEnlaceMethod())
              .addMethod(getGetEnlacesMethod())
              .addMethod(getGetClientesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
