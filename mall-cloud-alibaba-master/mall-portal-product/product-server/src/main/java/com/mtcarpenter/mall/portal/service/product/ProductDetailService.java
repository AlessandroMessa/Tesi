package com.mtcarpenter.mall.portal.service.product;

import com.mtcarpenter.mall.model.PmsProduct;
import com.mtcarpenter.mall.portal.domain.PmsPortalProductDetail;

public interface ProductDetailService {
    /**
     * 获取前台商品详情
     */
    PmsPortalProductDetail detail(Long id);

    /**
     * 获取商品详情
     */
    PmsProduct getPmsProductById(Long productId);
}
