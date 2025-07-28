package com.mtcarpenter.mall.portal.service.query.impl;

import com.mtcarpenter.mall.mapper.UmsMemberMapper;
import com.mtcarpenter.mall.model.UmsMember;
import com.mtcarpenter.mall.model.UmsMemberExample;
import com.mtcarpenter.mall.portal.domain.MemberDetails;
import com.mtcarpenter.mall.portal.service.UmsMemberCacheService;
import com.mtcarpenter.mall.portal.service.query.MemberQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class MemberQueryServiceImpl implements MemberQueryService {
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private UmsMemberCacheService memberCacheService;
    @Override
    public UmsMember getByUsername(String username) {
        UmsMember member = memberCacheService.getMember(username);
        if (member != null) {
            return member;
        }
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)) {
            member = memberList.get(0);
            memberCacheService.setMember(member);
            return member;
        }
        return null;
    }

    @Override
    public UmsMember getById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public UmsMember getCurrentMember() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
        return memberDetails.getUmsMember();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMember member = getByUsername(username);
        if (member != null) {
            return new MemberDetails(member);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

}
