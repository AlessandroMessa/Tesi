package com.mtcarpenter.mall.portal.service.query;

import com.mtcarpenter.mall.model.UmsMember;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberQueryService {
    /**
     * 根据用户名获取会员
     */
    UmsMember getByUsername(String username);

    /**
     * 根据会员编号获取会员
     */
    UmsMember getById(Long id);

    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

}
