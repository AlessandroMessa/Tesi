package com.mtcarpenter.mall.portal.service.order.impl;
import com.mtcarpenter.mall.client.CouponManagementClient;
import com.mtcarpenter.mall.client.MemberIntegrationClient;
import com.mtcarpenter.mall.mapper.OmsOrderItemMapper;
import com.mtcarpenter.mall.mapper.OmsOrderMapper;
import com.mtcarpenter.mall.mapper.OmsOrderSettingMapper;
import com.mtcarpenter.mall.model.*;
import com.mtcarpenter.mall.model.order.OmsOrderExample;
import com.mtcarpenter.mall.model.order.OmsOrderItemExample;
import com.mtcarpenter.mall.portal.component.CancelOrderSender;
import com.mtcarpenter.mall.portal.dao.PortalOrderDao;
import com.mtcarpenter.mall.portal.domain.OmsOrderDetail;
import com.mtcarpenter.mall.portal.service.order.OrderCancellationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderCancellationServiceImpl implements OrderCancellationService {

    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private PortalOrderDao portalOrderDao;
    @Autowired
    private OmsOrderSettingMapper orderSettingMapper;
    @Autowired
    private OmsOrderItemMapper orderItemMapper;
    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Autowired
    private CouponManagementClient couponManagementClient;

    @Autowired
    private MemberIntegrationClient memberIntegrationClient;

    @Override
    public Integer cancelTimeOutOrder() {
        Integer count = 0;
        OmsOrderSetting orderSetting = orderSettingMapper.selectByPrimaryKey(1L);
        //查询超时、未支付的订单及订单详情
        List<OmsOrderDetail> timeOutOrders = portalOrderDao.getTimeOutOrders(orderSetting.getNormalOrderOvertime());
        if (CollectionUtils.isEmpty(timeOutOrders)) {
            return count;
        }
        //修改订单状态为交易取消
        List<Long> ids = new ArrayList<>();
        for (OmsOrderDetail timeOutOrder : timeOutOrders) {
            ids.add(timeOutOrder.getId());
        }
        portalOrderDao.updateOrderStatus(ids, 4);
        for (OmsOrderDetail timeOutOrder : timeOutOrders) {
            //解除订单商品库存锁定
            portalOrderDao.releaseSkuStockLock(timeOutOrder.getOrderItemList());
            //修改优惠券使用状态
            couponManagementClient.updateCouponStatus(timeOutOrder.getCouponId(), timeOutOrder.getMemberId(), 0);
            //返还使用积分
            if (timeOutOrder.getUseIntegration() != null) {
                memberIntegrationClient.updateIntegration(timeOutOrder.getMemberId(), timeOutOrder.getUseIntegration());
            }
        }
        return timeOutOrders.size();
    }

    @Override
    public void sendDelayMessageCancelOrder(Long orderId) {
        //获取订单超时时间
        OmsOrderSetting orderSetting = orderSettingMapper.selectByPrimaryKey(1L);
        long delayTimes = orderSetting.getNormalOrderOvertime() * 60 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }

    @Override
    public void cancelOrder(Long orderId) {
        //查询未付款的取消订单
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andIdEqualTo(orderId).andStatusEqualTo(0).andDeleteStatusEqualTo(0);
        List<OmsOrder> cancelOrderList = orderMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(cancelOrderList)) {
            return;
        }
        OmsOrder cancelOrder = cancelOrderList.get(0);
        if (cancelOrder != null) {
            //修改订单状态为取消
            cancelOrder.setStatus(4);
            orderMapper.updateByPrimaryKeySelective(cancelOrder);
            OmsOrderItemExample orderItemExample = new OmsOrderItemExample();
            orderItemExample.createCriteria().andOrderIdEqualTo(orderId);
            List<OmsOrderItem> orderItemList = orderItemMapper.selectByExample(orderItemExample);
            //解除订单商品库存锁定
            if (!CollectionUtils.isEmpty(orderItemList)) {
                portalOrderDao.releaseSkuStockLock(orderItemList);
            }
            //修改优惠券使用状态
            couponManagementClient.updateCouponStatus(cancelOrder.getCouponId(), cancelOrder.getMemberId(), 0);
            //返还使用积分
            if (cancelOrder.getUseIntegration() != null) {
                memberIntegrationClient.updateIntegration(cancelOrder.getMemberId(), cancelOrder.getUseIntegration());
            }
        }
    }

}
