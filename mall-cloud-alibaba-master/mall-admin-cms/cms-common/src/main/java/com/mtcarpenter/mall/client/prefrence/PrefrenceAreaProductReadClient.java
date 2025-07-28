package com.mtcarpenter.mall.client.prefrence;

import com.mtcarpenter.mall.common.CmsPrefrenceAreaProductRelationInput;
import com.mtcarpenter.mall.common.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name = "mall-admin-cms", path = "prefrenceArea")
public interface PrefrenceAreaProductReadClient {
    /**
     * 通过id查询关联专题
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/relationByProductId", method = RequestMethod.GET)
    CommonResult<List<CmsPrefrenceAreaProductRelationInput>> relationByProductId(@RequestParam("productId") Long productId);
}
