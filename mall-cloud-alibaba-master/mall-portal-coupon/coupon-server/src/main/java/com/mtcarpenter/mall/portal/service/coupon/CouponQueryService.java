package com.mtcarpenter.mall.portal.service.coupon;

import com.mtcarpenter.mall.domain.CartPromotionItem;
import com.mtcarpenter.mall.domain.SmsCouponHistoryDetail;
import com.mtcarpenter.mall.model.SmsCoupon;
import com.mtcarpenter.mall.model.SmsCouponHistory;

import java.util.List;

public interface CouponQueryService {
    /**
     * 获取用户优惠券列表
     *
     * @param memberId
     * @param useStatus
     * @return
     */
    List<SmsCoupon> list(Long memberId, Integer useStatus);

    /**
     * 获取登录会员购物车的相关优惠券
     *
     * @param cartPromotionItemList
     * @param memberId
     * @param type
     * @return
     */
    List<SmsCouponHistoryDetail> listCart(List<CartPromotionItem> cartPromotionItemList, Long memberId, Integer type);
    /**
     *获取优惠券历史列表
     * @param memberId
     * @param useStatus
     * @return
     */
    List<SmsCouponHistory> listHistory(Long memberId, Integer useStatus);

    /**
     *获取当前商品相关优惠券
     * @param productId
     * @return
     */
    List<SmsCoupon> listByProduct(Long productId);

    /**
     * 商品优惠券
     * @param productId
     * @param productCategoryId
     * @return
     */
    List<SmsCoupon> getAvailableCouponList(Long productId, Long productCategoryId);

}
