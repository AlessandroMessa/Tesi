package com.mtcarpenter.mall.portal.service.product.impl;

import com.mtcarpenter.mall.mapper.PmsProductCategoryMapper;
import com.mtcarpenter.mall.model.PmsProductCategory;
import com.mtcarpenter.mall.model.PmsProductCategoryExample;
import com.mtcarpenter.mall.portal.domain.PmsProductCategoryNode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.mtcarpenter.mall.portal.service.product.ProductCategoryService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

public List<PmsProductCategoryNode> categoryTreeList() {
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        List<PmsProductCategory> allList = productCategoryMapper.selectByExample(example);
        List<PmsProductCategoryNode> result = allList.stream()
                .filter(item -> item.getParentId().equals(0L))
                .map(item -> covert(item, allList)).collect(Collectors.toList());
        return result;
    }

    /**
     * 初始对象转化为节点对象
     */
    private PmsProductCategoryNode covert(PmsProductCategory item, List<PmsProductCategory> allList) {
        PmsProductCategoryNode node = new PmsProductCategoryNode();
        BeanUtils.copyProperties(item, node);
        List<PmsProductCategoryNode> children = allList.stream()
                .filter(subItem -> subItem.getParentId().equals(item.getId()))
                .map(subItem -> covert(subItem, allList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}