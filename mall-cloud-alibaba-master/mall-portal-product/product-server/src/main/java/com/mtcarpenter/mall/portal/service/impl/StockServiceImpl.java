package com.mtcarpenter.mall.portal.service.impl;

import com.mtcarpenter.mall.mapper.PmsSkuStockMapper;
import com.mtcarpenter.mall.model.PmsSkuStock;
import com.mtcarpenter.mall.portal.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;

public class StockServiceImpl implements StockService {

    @Autowired
    private PmsSkuStockMapper skuStockMapper;

    /**
     * 锁定下单商品的所有库存
     *
     * @param productSkuId
     * @param quantity
     * @return
     */
    @Override
    public void lockStock(Long productSkuId, Integer quantity) {
        PmsSkuStock skuStock = skuStockMapper.selectByPrimaryKey(productSkuId);
        skuStock.setLockStock(skuStock.getLockStock() + quantity);
        skuStockMapper.updateByPrimaryKeySelective(skuStock);
    }
}
