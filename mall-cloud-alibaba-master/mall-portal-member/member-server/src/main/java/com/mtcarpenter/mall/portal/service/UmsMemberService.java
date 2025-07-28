package com.mtcarpenter.mall.portal.service;

import com.mtcarpenter.mall.portal.service.member.MemberAuthService;
import com.mtcarpenter.mall.portal.service.member.MemberIntegrationService;
import com.mtcarpenter.mall.portal.service.member.MemberQueryService;
import com.mtcarpenter.mall.portal.service.member.MemberRegistrationService;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 会员管理Service
 * Created by macro on 2018/8/3.
 */
public interface UmsMemberService extends MemberAuthService, MemberRegistrationService, MemberQueryService, MemberIntegrationService {

}
