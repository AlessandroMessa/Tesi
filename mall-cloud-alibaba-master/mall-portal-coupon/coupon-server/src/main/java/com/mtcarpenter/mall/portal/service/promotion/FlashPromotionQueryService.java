package com.mtcarpenter.mall.portal.service.promotion;

import com.mtcarpenter.mall.model.SmsFlashPromotion;
import com.mtcarpenter.mall.model.SmsFlashPromotionSession;

import java.util.Date;

public interface FlashPromotionQueryService {
    /**
     * 获取下一个场次
     *
     * @param date
     * @return
     */
    SmsFlashPromotionSession getNextFlashPromotionSession(Date date);

    /**
     * 根据时间获取秒杀活动
     *
     * @param date
     * @return
     */
    SmsFlashPromotion getFlashPromotion(Date date);

    /**
     * 根据时间获取秒杀场次
     *
     * @param date
     * @return
     */
    SmsFlashPromotionSession getFlashPromotionSession(Date date);
}
