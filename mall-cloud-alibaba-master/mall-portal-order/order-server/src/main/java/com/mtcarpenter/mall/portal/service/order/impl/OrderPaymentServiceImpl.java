package com.mtcarpenter.mall.portal.service.order.impl;

import com.mtcarpenter.mall.mapper.OmsOrderMapper;
import com.mtcarpenter.mall.model.OmsOrder;
import com.mtcarpenter.mall.portal.dao.PortalOrderDao;
import com.mtcarpenter.mall.portal.domain.OmsOrderDetail;
import com.mtcarpenter.mall.portal.service.order.OrderPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
@Slf4j
public class OrderPaymentServiceImpl implements OrderPaymentService {
    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private PortalOrderDao portalOrderDao;
    @Override
    public Integer paySuccess(Long orderId, Integer payType) {
        //修改订单支付状态
        OmsOrder order = new OmsOrder();
        order.setId(orderId);
        order.setStatus(1);
        order.setPaymentTime(new Date());
        order.setPayType(payType);
        orderMapper.updateByPrimaryKeySelective(order);
        //恢复所有下单商品的锁定库存，扣减真实库存
        OmsOrderDetail orderDetail = portalOrderDao.getDetail(orderId);
        int count = portalOrderDao.updateSkuStock(orderDetail.getOrderItemList());
        return count;
    }


}
