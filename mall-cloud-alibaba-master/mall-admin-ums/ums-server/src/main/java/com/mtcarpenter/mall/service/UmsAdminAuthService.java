package com.mtcarpenter.mall.service;


import org.springframework.security.core.userdetails.UserDetails;

import com.mtcarpenter.mall.dto.UmsAdminParam;
import com.mtcarpenter.mall.dto.UpdateAdminPasswordParam;
import com.mtcarpenter.mall.model.UmsAdmin;

public interface UmsAdminAuthService {
  /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);
  /**
   * Modifica password
   */
  int updatePassword(UpdateAdminPasswordParam updatePasswordParam);
   /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}