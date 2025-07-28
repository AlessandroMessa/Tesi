package com.mtcarpenter.mall.client.coupon;

import com.mtcarpenter.mall.common.api.CommonResult;
import com.mtcarpenter.mall.model.SmsCoupon;
import com.mtcarpenter.mall.model.SmsCouponHistory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CouponQueryClient {
    /**
     * 优惠券筛选类型:0->未使用；1->已使用；2->已过期
     *
     * @param memberId  会员id
     * @param useStatus 筛选
     * @return
     */
    @GetMapping(value = "/list")
    CommonResult<List<SmsCoupon>> list(@RequestParam(value = "memberId", required = false) Long memberId,
                                       @RequestParam(value = "useStatus", required = false) Integer useStatus);
    /**
     * 获取用户优惠券历史列表
     *
     * @param memberId
     * @param useStatus
     * @return
     */
    @GetMapping(value = "/listHistory")
    CommonResult<List<SmsCouponHistory>> listHistory(@RequestParam(value = "memberId", required = false) Long memberId,
                                                     @RequestParam(value = "useStatus", required = false) Integer useStatus);

    /**
     * 商品可用优惠券
     *
     * @param productId
     * @param productCategoryId
     * @return
     */
    @RequestMapping(value = "/getAvailableCouponList", method = RequestMethod.GET)
    CommonResult<List<SmsCoupon>> getAvailableCouponList(@RequestParam(value = "productId") Long productId,
                                                         @RequestParam(value = "productCategoryId") Long productCategoryId);
}
