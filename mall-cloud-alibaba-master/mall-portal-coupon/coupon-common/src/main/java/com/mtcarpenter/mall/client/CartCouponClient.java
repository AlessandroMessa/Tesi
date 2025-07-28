package com.mtcarpenter.mall.client;

import com.mtcarpenter.mall.common.api.CommonResult;
import com.mtcarpenter.mall.domain.CartPromotionItem;
import com.mtcarpenter.mall.domain.SmsCouponHistoryDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CartCouponClient {

    /**
     * 获取登录会员购物车的相关优惠券
     */
    @GetMapping(value = "/list/cart/{type}")
    CommonResult<List<SmsCouponHistoryDetail>> listCart(@PathVariable Integer type,
                                                        @RequestParam(value = "memberId", required = false) Long memberId);

    /**
     * 获取登录会员购物车的相关优惠券
     *
     * @param type
     * @param cartPromotionItemList
     * @param memberId
     * @return
     */
    @PostMapping(value = "/list/cart/{type}")
    CommonResult<List<SmsCouponHistoryDetail>> listCartPromotion(@PathVariable Integer type,
                                                                 List<CartPromotionItem> cartPromotionItemList,
                                                                 @RequestParam(value = "memberId", required = false) Long memberId);



}
