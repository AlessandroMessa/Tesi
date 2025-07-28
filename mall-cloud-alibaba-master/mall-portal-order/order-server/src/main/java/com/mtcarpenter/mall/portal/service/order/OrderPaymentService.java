package com.mtcarpenter.mall.portal.service.order;

import org.springframework.transaction.annotation.Transactional;

public interface OrderPaymentService {
    /**
     * 支付成功后的回调
     */
    @Transactional
    Integer paySuccess(Long orderId, Integer payType);
}
