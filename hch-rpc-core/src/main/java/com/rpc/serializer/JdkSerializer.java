package com.rpc.serializer;


import java.io.*;

public class JdkSerializer implements Serializer {

  /**
   * 序列化
   *
   * @param obj object 要序列化的对象
   * @param <T> 类型
   * @return 字节数组
   * @throws IOException io异常
   */
  @Override
  public <T> byte[] serialize(T obj) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
    objectOutputStream.writeObject(obj);
    objectOutputStream.close();
    return outputStream.toByteArray();
  }

  /**
   * 反序列化
   *
   * @param data  数据
   * @param clazz class对象
   * @param <T>   类型
   * @return 字节数组
   * @throws IOException io异常
   */
  @Override
  public <T> T deserialize(byte[] data, Class<T> clazz) throws IOException {
    ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
    ObjectInputStream stream = new ObjectInputStream(inputStream);
    try {
      return (T) stream.readObject();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } finally {
      stream.close();
    }
  }
}
