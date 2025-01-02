package com.rpc.proxy;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.rpc.model.RpcRequest;
import com.rpc.model.RpcResponse;
import com.rpc.serializer.JdkSerializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceProxy implements InvocationHandler {

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) {
    JdkSerializer serializer = new JdkSerializer();
    RpcRequest rpcRequest = RpcRequest.builder()
      .serviceName(method.getDeclaringClass().getName())
      .methodName(method.getName())
      .parameterTypes(method.getParameterTypes())
      .args(args)
      .build();

    try {
      byte[] serialize = serializer.serialize(rpcRequest);

      // todo 这里可以实现服务这次中心解决
      try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8077")
        .body(serialize)
        .execute()) {
        byte[] result = httpResponse.bodyBytes();
        RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
        return rpcResponse.getData();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
