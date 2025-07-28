package com.mtcarpenter.mall.client;

import com.mtcarpenter.mall.common.api.CommonResult;
import com.mtcarpenter.mall.domain.PromotionProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@FeignClient(name = "mall-portal-product", path = "product")
public interface PromotionProductClient {
    /**
     * 获取促销商品
     *
     * @param productIdList
     * @return
     */
    @RequestMapping(value = "/getPromotionProductList", method = RequestMethod.POST)
    CommonResult<List<PromotionProduct>> getPromotionProductList(@RequestBody(required = false) List<Long> productIdList);

}
