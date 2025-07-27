package com.mtcarpenter.mall.portal.service;

public interface StockService {
    /**
     * 锁定下单商品的所有库存
     */
    void lockStock(Long productSkuId, Integer quantity);
}
