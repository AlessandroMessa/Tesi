package com.mtcarpenter.mall.client;

import com.mtcarpenter.mall.common.api.CommonResult;
import com.mtcarpenter.mall.model.PmsProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(name = "mall-portal-product", path = "product")
public interface ProductQueryClient {
    /**
     * 获取商品详情
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/getPmsProductById/{productId}", method = RequestMethod.GET)
    CommonResult<PmsProduct> getPmsProductById(@PathVariable Long productId);
}
