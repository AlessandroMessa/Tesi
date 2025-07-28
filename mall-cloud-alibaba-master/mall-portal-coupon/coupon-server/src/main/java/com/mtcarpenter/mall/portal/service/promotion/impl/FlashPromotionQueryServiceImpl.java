package com.mtcarpenter.mall.portal.service.promotion.impl;

import com.mtcarpenter.mall.mapper.SmsFlashPromotionMapper;
import com.mtcarpenter.mall.mapper.SmsFlashPromotionSessionMapper;
import com.mtcarpenter.mall.model.SmsFlashPromotion;
import com.mtcarpenter.mall.model.SmsFlashPromotionExample;
import com.mtcarpenter.mall.model.SmsFlashPromotionSession;
import com.mtcarpenter.mall.model.SmsFlashPromotionSessionExample;
import com.mtcarpenter.mall.portal.service.promotion.FlashPromotionQueryService;
import com.mtcarpenter.mall.portal.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
@Service
public class FlashPromotionQueryServiceImpl implements FlashPromotionQueryService {

    @Autowired
    private SmsFlashPromotionSessionMapper promotionSessionMapper;
    @Autowired
    private SmsFlashPromotionMapper flashPromotionMapper;
    /**
     * 获取下一个场次
     *
     * @param date
     * @return
     */
    @Override
    public SmsFlashPromotionSession getNextFlashPromotionSession(Date date) {
        SmsFlashPromotionSessionExample sessionExample = new SmsFlashPromotionSessionExample();
        sessionExample.createCriteria()
                .andStartTimeGreaterThan(date);
        sessionExample.setOrderByClause("start_time asc");
        List<SmsFlashPromotionSession> promotionSessionList = promotionSessionMapper.selectByExample(sessionExample);
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }
    /**
     * 根据时间获取秒杀活动
     *
     * @param date
     * @return
     */
    @Override
    public SmsFlashPromotion getFlashPromotion(Date date) {
        Date currDate = DateUtil.getDate(date);
        SmsFlashPromotionExample example = new SmsFlashPromotionExample();
        example.createCriteria()
                .andStatusEqualTo(1)
                .andStartDateLessThanOrEqualTo(currDate)
                .andEndDateGreaterThanOrEqualTo(currDate);
        List<SmsFlashPromotion> flashPromotionList = flashPromotionMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(flashPromotionList)) {
            return flashPromotionList.get(0);
        }
        return null;
    }
    /**
     * 根据时间获取秒杀场次
     *
     * @param date
     * @return
     */
    @Override
    public SmsFlashPromotionSession getFlashPromotionSession(Date date) {
        Date currTime = DateUtil.getTime(date);
        SmsFlashPromotionSessionExample sessionExample = new SmsFlashPromotionSessionExample();
        sessionExample.createCriteria()
                .andStartTimeLessThanOrEqualTo(currTime)
                .andEndTimeGreaterThanOrEqualTo(currTime);
        List<SmsFlashPromotionSession> promotionSessionList = promotionSessionMapper.selectByExample(sessionExample);
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }


}
