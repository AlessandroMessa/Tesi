package com.mtcarpenter.mall.service;

import com.mtcarpenter.mall.model.UmsRole;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface UmsAdminRoleService {
  /** 修改用户角色关系 */
  @Transactional
  int updateRole(Long adminId, List<Long> roleIds);

  /** 获取用户对于角色 */
  List<UmsRole> getRoleList(Long adminId);
}