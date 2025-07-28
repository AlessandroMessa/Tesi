package com.mtcarpenter.mall.portal.service.command;

import org.springframework.transaction.annotation.Transactional;

public interface UmsMemberCouponCommandService {
    /**
     * 会员添加优惠券
     */
    @Transactional
    void add(Long couponId);
}