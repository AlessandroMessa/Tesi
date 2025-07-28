package com.mtcarpenter.mall.client.promotion;

import com.mtcarpenter.mall.common.api.CommonResult;
import com.mtcarpenter.mall.model.SmsFlashPromotion;
import com.mtcarpenter.mall.model.SmsFlashPromotionSession;
import com.mtcarpenter.mall.model.SmsHomeAdvertise;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface PromotionClient {


    /**
     * 获取下一个场次信息
     *
     * @param date
     * @return
     */
    @RequestMapping(value = "/getNextFlashPromotionSession", method = RequestMethod.GET)
    CommonResult<SmsFlashPromotionSession> getNextFlashPromotionSession(@RequestParam(value = "date") Date date);


    /**
     * 获取下一个场次信息
     *
     * @return
     */
    @RequestMapping(value = "/getHomeAdvertiseList", method = RequestMethod.GET)
    CommonResult<List<SmsHomeAdvertise>> getHomeAdvertiseList();


    /**
     * 根据时间获取秒杀活动
     *
     * @param date
     * @return
     */
    @RequestMapping(value = "/getFlashPromotion", method = RequestMethod.GET)
    CommonResult<SmsFlashPromotion> getFlashPromotion(@RequestParam(value = "date") Date date);

    /**
     * 根据时间获取秒杀场次
     *
     * @param date
     * @return
     */
    @RequestMapping(value = "/getFlashPromotionSession", method = RequestMethod.GET)
    CommonResult<SmsFlashPromotionSession> getFlashPromotionSession(@RequestParam(value = "date") Date date);
}
