package com.mtcarpenter.mall.domain.cart;

import com.mtcarpenter.mall.model.PmsProductAttribute;
import com.mtcarpenter.mall.model.PmsSkuStock;
import lombok.Data;

import java.util.List;
@Data
public class ProductOptions {
    private List<PmsProductAttribute> productAttributeList;
    private List<PmsSkuStock> skuStockList;

}
