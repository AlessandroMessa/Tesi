package com.mtcarpenter.mall.client;

import com.mtcarpenter.mall.common.api.CommonResult;
import com.mtcarpenter.mall.domain.CartProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(name = "mall-portal-product", path = "product")
public interface CartProductClient {
    /**
     * 获取购物车中某个商品的规格,用于重选规格
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/getProduct/{productId}", method = RequestMethod.GET)
    CommonResult<CartProduct> getCartProduct(@PathVariable Long productId);
}
