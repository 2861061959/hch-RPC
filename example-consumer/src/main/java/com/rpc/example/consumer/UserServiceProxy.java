package com.rpc.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.rpc.example.common.model.User;
import com.rpc.example.common.service.UserService;
import com.rpc.model.RpcRequest;
import com.rpc.model.RpcResponse;
import com.rpc.serializer.JdkSerializer;
import com.rpc.serializer.Serializer;

import java.io.IOException;

/**
 * 静态代理
 */
public class UserServiceProxy implements UserService {

  @Override
  public User getUser(User user) {
    // 指定序列化
    Serializer serializer = new JdkSerializer();

    // 构建请求对象
    RpcRequest rpcRequest = RpcRequest.builder()
      .serviceName(UserService.class.getName())
      .methodName("getUser")
      .parameterTypes(new Class[]{User.class})
      .args(new Object[]{user})
      .build();
    // 序列化发送请求
    try {
      byte[] bodyBytes = serializer.serialize(rpcRequest);
      byte[] result = null;
      try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8077")
        .body(bodyBytes)
        .execute()) {
        result = httpResponse.bodyBytes();
      }
      RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
      return (User) rpcResponse.getData();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
