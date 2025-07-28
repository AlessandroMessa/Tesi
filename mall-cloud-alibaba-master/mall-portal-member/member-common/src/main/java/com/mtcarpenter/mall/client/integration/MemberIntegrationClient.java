package com.mtcarpenter.mall.client.integration;

import com.mtcarpenter.mall.common.api.CommonResult;
import com.mtcarpenter.mall.model.UmsIntegrationConsumeSetting;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mall-portal-member")
public interface MemberIntegrationClient {
    /**
     * 获取积分使用规则
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/member/productCollection/integrationConsumeSetting", method = RequestMethod.GET)
    CommonResult<UmsIntegrationConsumeSetting> integrationConsumeSetting(@RequestParam(value = "id", defaultValue = "1") Long id);
    /**
     * 根据会员id修改会员积分
     *
     * @param id
     * @param integration
     * @return
     */
    @RequestMapping(value = "/sso/updateIntegration", method = RequestMethod.GET)
    CommonResult updateIntegration(@RequestParam("id") Long id, @RequestParam("integration") Integer integration);
}
