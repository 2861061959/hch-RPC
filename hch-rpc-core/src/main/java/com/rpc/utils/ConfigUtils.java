
package com.rpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

public class ConfigUtils {
    
    /**
     * 加载配置
     * @param <T>
     * @param clazz
     * @param prefix
     * @return
     */
    public static <T> T loadConfig(Class<T> clazz, String prefix) {
        return loadConfig(clazz, prefix,"");
    }

    /**
     * 加载配置
     * @param <T>
     * @param clazz
     * @param prefix
     * @param environment
     * @return
     */
    public static <T> T loadConfig(Class<T> clazz, String prefix, String environment) {
        StringBuilder configSBuilder = new StringBuilder("application");
        if(StrUtil.isNotBlank(environment)){
            configSBuilder.append("-").append(environment);
        }
        configSBuilder.append(".properties");
        Props props = new Props(configSBuilder.toString());
        return props.toBean(clazz, prefix);
    }
}