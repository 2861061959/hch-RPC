package com.rpc.serializer;

import java.io.IOException;

public interface Serializer {

  /**
   * 序列化
   * @param obj object
   * @param <T> 对象类型
   * @throws IOException
   */
  <T> byte[] serialize(T obj) throws IOException;


  /**
   * 反序列化
   * @param data 数据
   * @param clazz class对象
   * @return 返回对象
   * @param <T> 对象类型
   * @throws IOException
   */
  <T> T deserialize(byte[] data, Class<T> clazz) throws IOException;


}
