package com.mtcarpenter.mall.portal.service.order.impl;

import com.mtcarpenter.mall.common.exception.Asserts;
import com.mtcarpenter.mall.mapper.OmsOrderMapper;
import com.mtcarpenter.mall.model.OmsOrder;
import com.mtcarpenter.mall.model.UmsMember;
import com.mtcarpenter.mall.portal.service.order.OrderMutationService;
import com.mtcarpenter.mall.portal.util.MemberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Service
@Slf4j
public class OrderMutationServiceImpl implements OrderMutationService {
    @Autowired
    private MemberUtil memberUtil;
    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private HttpServletRequest request;
    @Override
    public void confirmReceiveOrder(Long orderId) {
        // redis 用户信息
        UmsMember member = memberUtil.getRedisUmsMember(request);
        OmsOrder order = orderMapper.selectByPrimaryKey(orderId);
        if (!member.getId().equals(order.getMemberId())) {
            Asserts.fail("不能确认他人订单！");
        }
        if (order.getStatus() != 2) {
            Asserts.fail("该订单还未发货！");
        }
        order.setStatus(3);
        order.setConfirmStatus(1);
        order.setReceiveTime(new Date());
        orderMapper.updateByPrimaryKey(order);
    }
    @Override
    public void deleteOrder(Long orderId) {
        // redis 用户信息
        UmsMember member = memberUtil.getRedisUmsMember(request);
        OmsOrder order = orderMapper.selectByPrimaryKey(orderId);
        if (!member.getId().equals(order.getMemberId())) {
            Asserts.fail("不能删除他人订单！");
        }
        if (order.getStatus() == 3 || order.getStatus() == 4) {
            order.setDeleteStatus(1);
            orderMapper.updateByPrimaryKey(order);
        } else {
            Asserts.fail("只能删除已完成或已关闭的订单！");
        }
    }

}
