package com.mtcarpenter.mall.portal.service;

import com.mtcarpenter.mall.portal.domain.PmsProductCategoryNode;
import java.util.List;

public interface ProductCategoryService {
    /**
     * 以树形结构获取所有商品分类
     */
    List<PmsProductCategoryNode> categoryTreeList();
}
