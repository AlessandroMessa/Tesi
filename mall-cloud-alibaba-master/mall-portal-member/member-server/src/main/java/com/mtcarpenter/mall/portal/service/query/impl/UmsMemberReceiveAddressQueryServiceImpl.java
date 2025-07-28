package com.mtcarpenter.mall.portal.service.query.impl;

import com.mtcarpenter.mall.mapper.UmsMemberReceiveAddressMapper;
import com.mtcarpenter.mall.model.UmsMember;
import com.mtcarpenter.mall.model.UmsMemberReceiveAddress;
import com.mtcarpenter.mall.model.member.UmsMemberReceiveAddressExample;
import com.mtcarpenter.mall.portal.service.query.MemberQueryService;
import com.mtcarpenter.mall.portal.service.query.UmsMemberReceiveAddressQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class UmsMemberReceiveAddressQueryServiceImpl implements UmsMemberReceiveAddressQueryService {
    @Autowired
    private MemberQueryService memberService;
    @Autowired
    private UmsMemberReceiveAddressMapper addressMapper;



    @Override
    public List<UmsMemberReceiveAddress> list(Long memberId) {
        UmsMember currentMember = memberService.getCurrentMember();
        if (memberId != null) {
            currentMember = memberService.getById(memberId);
        }
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId());
        return addressMapper.selectByExample(example);
    }

    @Override
    public UmsMemberReceiveAddress getItem(Long id) {
        UmsMember currentMember = memberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(id);
        List<UmsMemberReceiveAddress> addressList = addressMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(addressList)) {
            return addressList.get(0);
        }
        return null;
    }
}
