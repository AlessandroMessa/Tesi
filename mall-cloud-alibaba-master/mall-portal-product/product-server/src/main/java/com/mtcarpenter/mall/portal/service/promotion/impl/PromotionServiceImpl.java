package com.mtcarpenter.mall.portal.service.promotion.impl;

import com.mtcarpenter.mall.domain.PromotionProduct;
import com.mtcarpenter.mall.portal.dao.PortalProductDao;
import com.mtcarpenter.mall.portal.service.promotion.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PortalProductDao portalProductDao;
    /**
     * 获取促销商品
     *
     * @param productIdList
     * @return
     */
    @Override
    public List<PromotionProduct> getPromotionProductList(List<Long> productIdList) {
        return portalProductDao.getPromotionProductList(productIdList);
    }
}
