package com.dailyrecord.po;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017-4-5.
 */

@Component
@PropertySource("classpath:application.properties")
public class Config {
    //导出缓存目录

    private static String CONFIG_CACHE_DIR;

    public static String getConfigCacheDir() {
        return CONFIG_CACHE_DIR;
    }
    @Value("${config.cache.dir}")
    public void setConfigCacheDir(String configCacheDir) {
        CONFIG_CACHE_DIR = configCacheDir;
    }
}
