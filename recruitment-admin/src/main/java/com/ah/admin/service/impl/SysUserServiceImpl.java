package com.ah.admin.service.impl;

import com.ah.admin.mapper.SysUserMapper;
import com.ah.admin.service.ISysUserService;
import com.ah.api.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:55
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public SysUser selectUserByUserName(String username) {
        return sysUserMapper.selectUserByUserName(username);
    }

    @Override
    public SysUser selectUserByPhone(String phone) {
        return sysUserMapper.selectUserByPhone(phone);
    }
}

