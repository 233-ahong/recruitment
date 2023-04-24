package com.ah.admin.service.impl;

import com.ah.admin.domain.SysUserRole;
import com.ah.admin.mapper.SysUserRoleMapper;
import com.ah.admin.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:56
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

}

