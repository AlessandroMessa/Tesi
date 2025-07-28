package com.mtcarpenter.mall.portal.service.impl;


import com.mtcarpenter.mall.portal.service.member.MemberAuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mtcarpenter
 * @github https://github.com/mtcarpenter/mall-cloud-alibaba
 * @desc 微信公众号：山间木匠
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UmsMemberServiceImplTest  {


    @Autowired
    private MemberAuthService memberAuthService;

    @Test
    public void test(){
        String tset = memberAuthService.login("tset", "123456");
        System.out.println(tset);
    }
}