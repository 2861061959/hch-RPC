package com.rpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地注册中心
 */
public class LocalRegistry {

  /**
   * 注册服务信息存储
   */
  private static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

  /**
   * 注册服务
   *
   * @param serviceName 服务名称
   * @param implClazz   服务class
   */
  public static void registry(String serviceName, Class<?> implClazz) {
    map.put(serviceName, implClazz);
  }

  /**
   * 获取服务
   *
   * @param serviceName 服务名称
   * @return
   */
  public static Class<?> get(String serviceName) {
    return map.get(serviceName);
  }

  /**
   * 删除服务
   * @param serviceName 服务名称
   */
  public static void remove(String serviceName) {
    map.remove(serviceName);
  }
}
