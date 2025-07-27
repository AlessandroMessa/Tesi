package com.mtcarpenter.mall.portal.service;

import com.mtcarpenter.mall.domain.PromotionProduct;
import java.util.List;

public interface PromotionService {
    /**
     * 获取促销商品
     */
    List<PromotionProduct> getPromotionProductList(List<Long> productIdList);
}
