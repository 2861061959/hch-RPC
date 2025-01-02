package com.rpc.server;

import com.rpc.model.RpcRequest;
import com.rpc.model.RpcResponse;
import com.rpc.registry.LocalRegistry;
import com.rpc.serializer.JdkSerializer;
import com.rpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * http请求处理器
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {

  @Override
  public void handle(HttpServerRequest request) {
    // 指定序列化器
    final JdkSerializer serializer = new JdkSerializer();

    System.out.println("Received request: " + request.method() + " " + request.uri());

    request.bodyHandler(body -> {
      byte[] bodyBytes = body.getBytes();
      RpcRequest rpcRequest = null;

      try {
        rpcRequest = serializer.deserialize(bodyBytes, RpcRequest.class);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      RpcResponse response = new RpcResponse();
      if (rpcRequest == null) {
        response.setMessage("rpcRequest is null");
        doResponse(request, response, serializer);
        return;
      }
      Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
      System.out.println("implClass: " + implClass);
      try {
        Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
        Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
        // 封装返回结果
        response.setData(result);
        response.setMessage("ok");
        response.setDataType(method.getReturnType());
      } catch (Exception e) {
        e.printStackTrace();
        response.setMessage(e.getMessage());
        response.setException(e);
      }
      doResponse(request, response, serializer);
    });
  }

  /**
   * 响应
   *
   * @param request     设置响应
   * @param rpcResponse 响应对象
   * @param serializer  序列化对象
   */
  void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
    HttpServerResponse httpServerResponse = request.response()
      .putHeader("content-type", "application/json");

    try {
      byte[] serialize = serializer.serialize(rpcResponse);
      httpServerResponse.end(Buffer.buffer(serialize));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
