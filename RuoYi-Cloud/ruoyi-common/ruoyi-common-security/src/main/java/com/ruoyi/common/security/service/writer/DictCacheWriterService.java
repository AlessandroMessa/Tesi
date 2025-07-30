package com.ruoyi.common.security.service.writer;

import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.service.cachekey.DictCacheKeyHelper;
import com.ruoyi.system.api.dict.domain.SysDictData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictCacheWriterService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private DictCacheKeyHelper keyHelper;

    public void setDictCache(String key, List<SysDictData> dictDatas) {
        redisService.setCacheObject(keyHelper.getCacheKey(key), dictDatas);
    }
}
