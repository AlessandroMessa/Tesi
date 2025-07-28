package com.mtcarpenter.mall.portal.service.command.impl;

import com.mtcarpenter.mall.mapper.UmsMemberReceiveAddressMapper;
import com.mtcarpenter.mall.model.UmsMember;
import com.mtcarpenter.mall.model.UmsMemberReceiveAddress;
import com.mtcarpenter.mall.model.UmsMemberReceiveAddressExample;
import com.mtcarpenter.mall.portal.service.command.UmsMemberReceiveAddressCommandService;
import com.mtcarpenter.mall.portal.service.query.MemberQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsMemberReceiveAddressCommandServiceImpl implements UmsMemberReceiveAddressCommandService {
    @Autowired
    private MemberQueryService memberService;
    @Autowired
    private UmsMemberReceiveAddressMapper addressMapper;

    @Override
    public int add(UmsMemberReceiveAddress address) {
        UmsMember currentMember = memberService.getCurrentMember();
        address.setMemberId(currentMember.getId());
        return addressMapper.insert(address);
    }

    @Override
    public int delete(Long id) {
        UmsMember currentMember = memberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(id);
        return addressMapper.deleteByExample(example);
    }

    @Override
    public int update(Long id, UmsMemberReceiveAddress address) {
        address.setId(null);
        UmsMember currentMember = memberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(id);
        if (address.getDefaultStatus() == 1) {
            //先将原来的默认地址去除
            UmsMemberReceiveAddress record = new UmsMemberReceiveAddress();
            record.setDefaultStatus(0);
            UmsMemberReceiveAddressExample updateExample = new UmsMemberReceiveAddressExample();
            updateExample.createCriteria()
                    .andMemberIdEqualTo(currentMember.getId())
                    .andDefaultStatusEqualTo(1);
            addressMapper.updateByExampleSelective(record, updateExample);
        }
        return addressMapper.updateByExampleSelective(address, example);
    }
}
