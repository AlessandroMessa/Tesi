package com.mtcarpenter.mall.client.subject;

import com.mtcarpenter.mall.common.CmsSubjectProductRelationInput;
import com.mtcarpenter.mall.common.api.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SubjectProductReadClient {

    /**
     * 通过id查询关联优选
     * @param productId
     * @return
     */
    @RequestMapping(value = "/relationByProductId", method = RequestMethod.GET)
    CommonResult<List<CmsSubjectProductRelationInput>> relationByProductId(@RequestParam("productId") Long productId ) ;

}
