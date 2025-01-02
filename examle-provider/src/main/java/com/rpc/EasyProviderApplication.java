package com.rpc;

import com.rpc.example.common.service.UserService;
import com.rpc.example.provider.UserServiceImpl;
import com.rpc.registry.LocalRegistry;
import com.rpc.server.HttpServer;
import com.rpc.server.VerxHttpServer;

public class EasyProviderApplication {
  public static void main(String[] args) {
    // 注册服务
    LocalRegistry.registry(UserService.class.getName(), UserServiceImpl.class);

    System.out.println(LocalRegistry.get(UserService.class.getName()));
    // 启动服务器
    HttpServer httpServer = new VerxHttpServer();
    httpServer.doStart(8077);
  }
}