package com.mtcarpenter.mall.portal.service.command.impl;

import com.mtcarpenter.mall.client.CouponFeign;
import com.mtcarpenter.mall.model.UmsMember;
import com.mtcarpenter.mall.portal.service.UmsMemberService;
import com.mtcarpenter.mall.portal.service.command.UmsMemberCouponCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsMemberCouponCommandServiceImpl implements UmsMemberCouponCommandService {
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private CouponFeign couponFeign;

    @Override
    public void add(Long couponId) {
        UmsMember currentMember = memberService.getCurrentMember();
        // 远程接口 添加优惠券
        couponFeign.add(couponId, currentMember.getId(), currentMember.getNickname());
    }

}
