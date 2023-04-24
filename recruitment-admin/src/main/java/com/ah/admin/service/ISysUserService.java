package com.ah.admin.service;

import com.ah.api.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户信息表(SysUser)表服务接口
 *
 * @author 26471
 * @since 2023-04-15 20:41:55
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser selectUserByUserName(String username);

    SysUser selectUserByPhone(String phone);
}

