package com.mtcarpenter.mall.domain.cart;


import com.mtcarpenter.mall.model.PmsProductAttribute;
import com.mtcarpenter.mall.model.PmsProduct;
import com.mtcarpenter.mall.model.PmsSkuStock;
import lombok.Data;

import java.util.List;

/**
 * 购物车中选择规格的商品信息
 * Created by macro on 2018/8/2.
 */
@Data
public class CartProduct extends PmsProduct {
    private ProductOptions options;

    // Per compatibilità con codice esistente
    public List<PmsProductAttribute> getProductAttributeList() {
        return options != null ? options.getProductAttributeList() : null;
    }

    public void setProductAttributeList(List<PmsProductAttribute> list) {
        if (options == null) options = new ProductOptions();
        options.setProductAttributeList(list);
    }

    public List<PmsSkuStock> getSkuStockList() {
        return options != null ? options.getSkuStockList() : null;
    }

    public void setSkuStockList(List<PmsSkuStock> list) {
        if (options == null) options = new ProductOptions();
        options.setSkuStockList(list);
    }
}
