package com.mtcarpenter.mall.portal.service.member;

import org.springframework.security.core.userdetails.UserDetails;

public interface MemberAuthService {
    /**
     * 登录后获取token
     */
    String login(String username, String password);

    /**
     * 刷新token
     */
    String refreshToken(String token);

}
