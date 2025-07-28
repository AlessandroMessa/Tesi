package com.mtcarpenter.mall.portal.service.order;

import com.mtcarpenter.mall.portal.domain.ConfirmOrderResult;
import com.mtcarpenter.mall.portal.domain.OrderParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface OrderGenerationService {
    /**
     * 根据用户购物车信息生成确认单信息
     * @param cartIds
     */
    ConfirmOrderResult generateConfirmOrder(List<Long> cartIds);

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    Map<String, Object> generateOrder(OrderParam orderParam);
}
