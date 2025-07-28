package com.mtcarpenter.mall.client.address;

import com.mtcarpenter.mall.common.api.CommonResult;
import com.mtcarpenter.mall.model.UmsMemberReceiveAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name = "mall-portal-member")
public interface MemberAddressClient {

    /**
     * 显示所有收货地址
     *
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/member/address/list", method = RequestMethod.GET)
    CommonResult<List<UmsMemberReceiveAddress>> list(@RequestParam(value = "memberId", required = false) Long memberId);

    /**
     * 获取收货地址详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/member/address/{id}", method = RequestMethod.GET)
    CommonResult<UmsMemberReceiveAddress> getItem(@PathVariable Long id);

}
