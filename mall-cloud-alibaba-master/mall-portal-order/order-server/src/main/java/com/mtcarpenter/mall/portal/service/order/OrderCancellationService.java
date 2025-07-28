package com.mtcarpenter.mall.portal.service.order;

import org.springframework.transaction.annotation.Transactional;

public interface OrderCancellationService {
    /**
     * 自动取消超时订单
     */
    @Transactional
    Integer cancelTimeOutOrder();

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);

    /**
     * 发送延迟消息取消订单
     */
    void sendDelayMessageCancelOrder(Long orderId);


}
