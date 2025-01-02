package com.rpc.server;


import io.vertx.core.Vertx;

public class VerxHttpServer implements HttpServer {

  @Override
  public void doStart(int port) {
    Vertx vertx = Vertx.vertx();
    io.vertx.core.http.HttpServer httpServer = vertx.createHttpServer();
    // 监听并处理请求
    httpServer.requestHandler(request -> {
      System.out.println("Received request: " + request.method() + " " + request.uri());
    });

    httpServer.requestHandler(new HttpServerHandler());

    httpServer.listen(port, result -> {
      if (result.succeeded()) {
        System.out.println("Server is now listening on port " + port);
      } else {
        System.out.println("Failed to start server" + result.cause());
      }
    });

  }
}
