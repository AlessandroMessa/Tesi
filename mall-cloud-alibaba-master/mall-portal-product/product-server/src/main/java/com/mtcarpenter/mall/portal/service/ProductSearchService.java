package com.mtcarpenter.mall.portal.service;

import com.mtcarpenter.mall.model.PmsProduct;
import java.util.List;

public interface ProductSearchService {
    /**
     * 综合搜索商品
     */
    List<PmsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort);
}
