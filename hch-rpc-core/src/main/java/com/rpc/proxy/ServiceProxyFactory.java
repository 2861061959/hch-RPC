package com.rpc.proxy;

import java.lang.reflect.Proxy;

/**
 * 工厂服务代理模式
 */
public class ServiceProxyFactory {

  public static <T> T getProxy(Class<T> clazz) {
    return (T) Proxy.newProxyInstance(
            clazz.getClassLoader(),
            new Class[]{clazz},
            new ServiceProxy());
  }
}
