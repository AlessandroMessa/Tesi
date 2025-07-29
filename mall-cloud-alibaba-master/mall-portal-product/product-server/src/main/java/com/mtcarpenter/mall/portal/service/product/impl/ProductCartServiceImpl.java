package com.mtcarpenter.mall.portal.service.product.impl;

import com.mtcarpenter.mall.domain.cart.CartProduct;
import com.mtcarpenter.mall.portal.dao.PortalProductDao;
import com.mtcarpenter.mall.portal.service.product.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCartServiceImpl implements ProductCartService {
    @Autowired
    private PortalProductDao portalProductDao;
    /**
     * 获取购物车中某个商品的规格,用于重选规格
     *
     * @param productId
     * @return
     */
    @Override
    public CartProduct getCartProduct(Long productId) {
        return portalProductDao.getCartProduct(productId);
    }
}
