package com.mtcarpenter.mall.portal.service.command;

import com.mtcarpenter.mall.model.UmsMemberReceiveAddress;
import org.springframework.transaction.annotation.Transactional;

public interface UmsMemberReceiveAddressCommandService {
    /**
     * 添加收货地址
     */
    int add(UmsMemberReceiveAddress address);

    /**
     * 删除收货地址
     *
     * @param id 地址表的id
     */
    int delete(Long id);

    /**
     * 修改收货地址
     *
     * @param id      地址表的id
     * @param address 修改的收货地址信息
     */
    @Transactional
    int update(Long id, UmsMemberReceiveAddress address);
}
