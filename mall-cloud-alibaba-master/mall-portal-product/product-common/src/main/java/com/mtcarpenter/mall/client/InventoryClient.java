package com.mtcarpenter.mall.client;

import com.mtcarpenter.mall.common.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "mall-portal-product", path = "product")
public interface InventoryClient {

    /**
     * 锁定下单商品的所有库存
     *
     * @param productSkuId
     * @param quantity
     * @return
     */
    @RequestMapping(value = "/lockStock", method = RequestMethod.POST)
    CommonResult lockStock(@RequestParam(required = false) Long productSkuId, @RequestParam(required = false) Integer quantity);

}
