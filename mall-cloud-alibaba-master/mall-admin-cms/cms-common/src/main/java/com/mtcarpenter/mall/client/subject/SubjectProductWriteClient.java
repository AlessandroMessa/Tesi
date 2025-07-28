package com.mtcarpenter.mall.client.subject;

import com.mtcarpenter.mall.common.CmsSubjectProductRelationInput;
import com.mtcarpenter.mall.common.api.CommonResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SubjectProductWriteClient {
    /**
     * 关联优选
     *
     * @param productRelationInputs
     */
    @RequestMapping(value = "/relateAndInsertList", method = RequestMethod.POST)
    CommonResult relateAndInsertList(@RequestBody List<CmsSubjectProductRelationInput> productRelationInputs, @RequestParam("productId") Long productId);

    /**
     * 批量更新关联优选
     * @param productRelationInputs
     * @param productId
     */
    @RequestMapping(value = "/relateAndUpdateList", method = RequestMethod.POST)
    CommonResult relateAndUpdateList(@RequestBody List<CmsSubjectProductRelationInput> productRelationInputs,@RequestParam("productId") Long productId);

}
