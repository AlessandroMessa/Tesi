package com.mtcarpenter.mall.portal.service.order;

import com.mtcarpenter.mall.common.api.CommonPage;
import com.mtcarpenter.mall.portal.domain.OmsOrderDetail;

public interface OrderQueryService {
    /**
     * 分页获取用户订单
     */
    CommonPage<OmsOrderDetail> list(Integer status, Integer pageNum, Integer pageSize);

    /**
     * 根据订单ID获取订单详情
     */
    OmsOrderDetail detail(Long orderId);
}
