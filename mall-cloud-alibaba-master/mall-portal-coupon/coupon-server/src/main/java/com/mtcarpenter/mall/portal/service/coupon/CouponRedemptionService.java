package com.mtcarpenter.mall.portal.service.coupon;

public interface CouponRedemptionService {
    /**
     * 添加优惠券
     *
     * @param couponId
     * @param memberId
     * @param nickName
     */
    void add(Long couponId, Long memberId, String nickName);
    /**
     * 将优惠券信息更改为指定状态
     *
     * @param couponId
     * @param memberId
     * @param useStatus
     */
    void updateCouponStatus(Long couponId, Long memberId, Integer useStatus);

}
