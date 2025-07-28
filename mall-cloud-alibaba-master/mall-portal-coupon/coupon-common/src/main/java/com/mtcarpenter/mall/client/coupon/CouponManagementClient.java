package com.mtcarpenter.mall.client.coupon;

import com.mtcarpenter.mall.common.api.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface CouponManagementClient {
    /**
     * 条件优惠券
     *
     * @param couponId
     * @param memberId
     * @param nickName
     * @return
     */
    @PostMapping(value = "/add/{couponId}")
    CommonResult add(@PathVariable Long couponId, @RequestParam("memberId") Long memberId, @RequestParam("nickName") String nickName);

    /**
     * 将优惠券信息更改为指定状态
     *
     * @param couponId  优惠券id
     * @param memberId  会员id
     * @param useStatus 0->未使用；1->已使用
     */
    @GetMapping(value = "/updateCouponStatus")
    CommonResult updateCouponStatus(@RequestParam(value = "couponId") Long couponId,
                                    @RequestParam(value = "memberId") Long memberId,
                                    @RequestParam(value = "useStatus") Integer useStatus);


}
