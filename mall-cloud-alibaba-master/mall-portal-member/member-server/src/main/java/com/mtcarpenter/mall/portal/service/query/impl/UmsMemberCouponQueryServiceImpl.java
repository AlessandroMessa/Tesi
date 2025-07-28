package com.mtcarpenter.mall.portal.service.query.impl;

import com.mtcarpenter.mall.client.CouponFeign;
import com.mtcarpenter.mall.common.api.CommonResult;
import com.mtcarpenter.mall.common.api.ResultCode;
import com.mtcarpenter.mall.domain.SmsCouponHistoryDetail;
import com.mtcarpenter.mall.model.SmsCoupon;
import com.mtcarpenter.mall.model.SmsCouponHistory;
import com.mtcarpenter.mall.model.UmsMember;
import com.mtcarpenter.mall.portal.service.member.MemberQueryService;
import com.mtcarpenter.mall.portal.service.query.UmsMemberCouponQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UmsMemberCouponQueryServiceImpl implements UmsMemberCouponQueryService {
    @Autowired
    private MemberQueryService memberService;
    @Autowired
    private CouponFeign couponFeign;

    @Override
    public List<SmsCoupon> list(Integer useStatus) {
        UmsMember currentMember = memberService.getCurrentMember();
        CommonResult<List<SmsCoupon>> result = couponFeign.list(currentMember.getId(), useStatus);
        if (result.getCode() == ResultCode.SUCCESS.getCode()) {
            return result.getData();
        }
        return null;
    }

    @Override
    public List<SmsCouponHistoryDetail> listCart(Integer type) {
        UmsMember currentMember = memberService.getCurrentMember();
        CommonResult<List<SmsCouponHistoryDetail>> result = couponFeign.listCart(type, currentMember.getId());
        if (result.getCode() == ResultCode.SUCCESS.getCode()) {
            return result.getData();
        }
        return null;
    }

    /**
     * 获取优惠券历史列表
     *
     * @param useStatus
     * @return
     */
    @Override
    public List<SmsCouponHistory> listHistory(Integer useStatus) {
        UmsMember currentMember = memberService.getCurrentMember();
        CommonResult<List<SmsCouponHistory>> result = couponFeign.listHistory(currentMember.getId(), useStatus);
        if (result.getCode() == ResultCode.SUCCESS.getCode()) {
            return result.getData();
        }
        return null;
    }
}
