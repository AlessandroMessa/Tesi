package com.ruoyi.common.security.service.reader;

import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.service.cachekey.DictCacheKeyHelper;
import com.ruoyi.system.api.dict.domain.SysDictData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictCacheReaderService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private DictCacheKeyHelper keyHelper;



    public List<SysDictData> getDictCache(String key) {
        JSONArray arrayCache = redisService.getCacheObject(keyHelper.getCacheKey(key));
        return arrayCache != null ? arrayCache.toList(SysDictData.class) : null;
    }

}
