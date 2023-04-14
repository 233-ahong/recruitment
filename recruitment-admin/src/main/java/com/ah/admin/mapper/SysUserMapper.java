package com.ah.admin.mapper;

import com.ah.api.domain.SysUser;

/**
 * @author 26471
 * @date 2023/4/4 17:41
 * @description
 */
public interface SysUserMapper {
    public SysUser selectUserByUserName(String userName);

    SysUser selectUserByPhone(String phone);
}
