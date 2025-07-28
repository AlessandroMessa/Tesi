package com.mtcarpenter.mall.portal.service.order;

public interface OrderMutationService {
    /**
     * 确认收货
     */
    void confirmReceiveOrder(Long orderId);



    /**
     * 用户根据订单ID删除订单
     */
    void deleteOrder(Long orderId);
}
