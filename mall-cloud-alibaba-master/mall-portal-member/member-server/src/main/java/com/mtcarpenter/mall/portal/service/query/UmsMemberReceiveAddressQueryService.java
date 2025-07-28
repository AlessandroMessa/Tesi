package com.mtcarpenter.mall.portal.service.query;

import com.mtcarpenter.mall.model.UmsMemberReceiveAddress;

import java.util.List;

public interface UmsMemberReceiveAddressQueryService {
    /**
     * 返回当前用户的收货地址
     *
     * @param memberId
     * @return
     */
    List<UmsMemberReceiveAddress> list(Long memberId);

    /**
     * 获取地址详情
     *
     * @param id 地址id
     */
    UmsMemberReceiveAddress getItem(Long id);
}
