package com.mtcarpenter.mall.portal.service.home.impl;

import com.mtcarpenter.mall.mapper.SmsHomeAdvertiseMapper;
import com.mtcarpenter.mall.model.SmsHomeAdvertise;
import com.mtcarpenter.mall.model.SmsHomeAdvertiseExample;
import com.mtcarpenter.mall.portal.service.home.HomeAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HomeAdvertiseServiceImpl implements HomeAdvertiseService {
    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;
    /**
     * 获取首页广告
     *
     * @return
     */
    @Override
    public List<SmsHomeAdvertise> getHomeAdvertiseList() {
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.createCriteria().andTypeEqualTo(1).andStatusEqualTo(1);
        example.setOrderByClause("sort desc");
        return advertiseMapper.selectByExample(example);
    }

}
