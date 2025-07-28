package com.mtcarpenter.mall.service.admin;

import com.mtcarpenter.mall.model.UmsAdmin;
import java.util.List;

public interface UmsAdminProfileService {
  /** 根据用户名获取后台管理员 */
  UmsAdmin getAdminByUsername(String username);

  /** 根据用户id获取用户 */
  UmsAdmin getItem(Long id);

  /** 根据用户名或昵称分页查询用户 */
  List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

  /** 修改指定用户信息 */
  int update(Long id, UmsAdmin admin);

  /** 删除指定用户 */
  int delete(Long id);
}