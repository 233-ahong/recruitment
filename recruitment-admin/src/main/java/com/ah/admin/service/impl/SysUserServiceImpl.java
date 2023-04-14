package com.ah.admin.service.impl;

import com.ah.admin.mapper.SysUserMapper;
import com.ah.admin.service.ISysUserService;
import com.ah.api.domain.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 26471
 * @date 2023/4/4 17:38
 * @description
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public SysUser selectUserByUserName(String userName) {
        return sysUserMapper.selectUserByUserName(userName);
    }

    @Override
    public SysUser selectUserByPhone(String phone) {
        return sysUserMapper.selectUserByPhone(phone);
    }
}
