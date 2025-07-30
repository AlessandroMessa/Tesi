package com.ruoyi.common.security.service.cachekey;

import org.springframework.stereotype.Component;
import com.ruoyi.common.core.constant.CacheConstants;

@Component
public class DictCacheKeyHelper {

    public static final String PREFIX = CacheConstants.SYS_DICT_KEY;

    public String getCacheKey(String configKey) {
        return PREFIX + configKey;
    }
}
