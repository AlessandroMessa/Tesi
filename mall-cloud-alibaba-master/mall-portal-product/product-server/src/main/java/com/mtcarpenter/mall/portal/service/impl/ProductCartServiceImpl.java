package com.mtcarpenter.mall.portal.service.impl;

import com.mtcarpenter.mall.domain.CartProduct;
import com.mtcarpenter.mall.portal.dao.PortalProductDao;
import com.mtcarpenter.mall.portal.service.ProductCartService;
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
