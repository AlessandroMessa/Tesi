package com.mtcarpenter.mall.service.product.query;

import com.mtcarpenter.mall.common.PmsProductOutput;
import com.mtcarpenter.mall.dto.PmsProductQueryParam;
import com.mtcarpenter.mall.model.product.PmsProduct;

import java.util.List;

public interface ProductQueryService {
    /**
     * 分页查询商品
     */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);
    /**
     * 根据商品名称或者货号模糊查询
     */
    List<PmsProduct> list(String keyword);

    /**
     * 根据 商品 id 过去商品信息
     * @param productId
     * @return
     */
    PmsProductOutput getProductByProductId(Long productId);
}
