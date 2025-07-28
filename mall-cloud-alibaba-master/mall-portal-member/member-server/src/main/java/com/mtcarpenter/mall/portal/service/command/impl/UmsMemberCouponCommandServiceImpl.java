package com.mtcarpenter.mall.portal.service.command.impl;

import com.mtcarpenter.mall.client.CouponManagementClient;
import com.mtcarpenter.mall.model.UmsMember;
import com.mtcarpenter.mall.portal.service.query.MemberQueryService;
import com.mtcarpenter.mall.portal.service.command.UmsMemberCouponCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsMemberCouponCommandServiceImpl implements UmsMemberCouponCommandService {
    @Autowired
    private MemberQueryService memberService;
    @Autowired
    private CouponManagementClient managementClient;

    @Override
    public void add(Long couponId) {
        UmsMember currentMember = memberService.getCurrentMember();
        // 远程接口 添加优惠券
        managementClient.add(couponId, currentMember.getId(), currentMember.getNickname());
    }

}
