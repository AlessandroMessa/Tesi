package com.mtcarpenter.mall.portal.service.home;

import com.mtcarpenter.mall.model.SmsHomeAdvertise;

import java.util.List;

public interface HomeAdvertiseService {

    /**
     * 获取首页广告
     *
     * @return
     */
    List<SmsHomeAdvertise> getHomeAdvertiseList();
}
