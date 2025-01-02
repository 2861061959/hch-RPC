package com.rpc;

import com.rpc.constant.RpcConstant;
import com.rpc.config.RpcConfig;
import com.rpc.utils.ConfigUtils;


public class RpcApplication {

  private static volatile RpcConfig rpcConfig;

  public static void init(RpcConfig newRpcConfig) {
    rpcConfig = newRpcConfig;
  }

  public static void init() {
    RpcConfig newRpcConfig = null;
    try {
      RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
      init(rpcConfig);
    } catch (Exception e) {
      newRpcConfig = new RpcConfig();
    }
    init(newRpcConfig);
  }


  public static RpcConfig getRpcConfig() {
    if(null == rpcConfig) {
      synchronized (RpcApplication.class) {
        if(null == rpcConfig) {
          init();
        }
      }
    }
    return rpcConfig;
  }
  
}

