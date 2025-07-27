package com.mtcarpenter.mall.portal.service;

import com.mtcarpenter.mall.domain.CartProduct;

public interface ProductCartService {
    /**
     * 获取购物车中某个商品的规格,用于重选规格
     */
    CartProduct getCartProduct(Long productId);
}
