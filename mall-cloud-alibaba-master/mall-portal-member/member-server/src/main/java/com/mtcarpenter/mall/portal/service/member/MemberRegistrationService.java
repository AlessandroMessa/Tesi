package com.mtcarpenter.mall.portal.service.member;

import org.springframework.transaction.annotation.Transactional;

public interface MemberRegistrationService {
    /**
     * 用户注册
     */
    @Transactional
    void register(String username, String password, String telephone, String authCode);

    /**
     * 生成验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 修改密码
     */
    @Transactional
    void updatePassword(String telephone, String password, String authCode);
}
