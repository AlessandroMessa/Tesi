package com.mtcarpenter.mall.portal.service.member.impl;

import com.mtcarpenter.mall.mapper.UmsMemberMapper;
import com.mtcarpenter.mall.model.UmsMember;
import com.mtcarpenter.mall.portal.service.UmsMemberCacheService;
import com.mtcarpenter.mall.portal.service.member.MemberIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberIntegrationServiceImpl implements MemberIntegrationService {
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private UmsMemberCacheService memberCacheService;
    @Override
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record = new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        memberMapper.updateByPrimaryKeySelective(record);
        memberCacheService.delMember(id);
    }


}
