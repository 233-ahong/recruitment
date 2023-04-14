package com.ah.admin.service;

import com.ah.api.domain.SysUser;

/**
 * @author 26471
 */
public interface ISysUserService {

    SysUser selectUserByUserName(String username);

    SysUser selectUserByPhone(String phone);
}
