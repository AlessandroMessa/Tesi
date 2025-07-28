package com.mtcarpenter.mall.portal.service.member;

public interface MemberIntegrationService {
    /**
     * 根据会员id修改会员积分
     */
    void updateIntegration(Long id, Integer integration);
}
