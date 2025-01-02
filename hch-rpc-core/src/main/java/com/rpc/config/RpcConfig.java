package com.rpc.config;

import lombok.Data;

/**
 * 配置
 */

@Data
public class RpcConfig {

    /**
     * 名称
     */ 
    private String name = "hch-rpc-core";

    /**
     * 版本
     */
    private String version = "1.0.0";

    /**
     * 服务地址
     */
    private String serverHost = "localhost";

    /**
     * 服务端口
     */
    private Integer serverPort = 8077;

}
