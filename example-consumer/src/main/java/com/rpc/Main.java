package com.rpc;

import com.rpc.config.RpcConfig;
import com.rpc.utils.ConfigUtils;

public class Main {
  public static void main(String[] args) {
    RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
    System.out.println(rpc);
  }
}
