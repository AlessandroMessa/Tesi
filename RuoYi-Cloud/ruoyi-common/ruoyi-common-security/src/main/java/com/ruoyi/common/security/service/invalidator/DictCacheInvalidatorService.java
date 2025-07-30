package com.ruoyi.common.security.service.invalidator;

import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.service.cachekey.DictCacheKeyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DictCacheInvalidatorService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private DictCacheKeyHelper keyHelper;

    public void removeDictCache(String key) {
        redisService.deleteObject(keyHelper.getCacheKey(key));
    }

    public void clearDictCache() {
        Collection<String> keys = redisService.keys(DictCacheKeyHelper.PREFIX + "*");
        redisService.deleteObject(keys);
    }
}
