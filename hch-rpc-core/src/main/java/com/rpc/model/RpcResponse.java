package com.rpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RpcResponse implements Serializable {

  /**
   * 响应数据
   */
  private Object data;

  /**
   * 响应数据类型
   */
  private Class<?> dataType;

  /**
   * 响应数据信息
   */
  private String message;

  /**
   * 响应异常
   */
  private Exception exception;
  private static final long serialVersionUID = 1L;
}
