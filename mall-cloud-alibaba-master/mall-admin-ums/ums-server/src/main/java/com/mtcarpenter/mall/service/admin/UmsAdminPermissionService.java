package com.mtcarpenter.mall.service.admin;

import com.mtcarpenter.mall.model.permission.UmsPermission;
import com.mtcarpenter.mall.model.permission.UmsResource;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface UmsAdminPermissionService {
  /** 获取指定用户的可访问资源 */
  List<UmsResource> getResourceList(Long adminId);

  /** 修改用户的+-权限 */
  @Transactional
  int updatePermission(Long adminId, List<Long> permissionIds);

  /** 获取用户所有权限（包括角色权限和+-权限） */
  List<UmsPermission> getPermissionList(Long adminId);
}