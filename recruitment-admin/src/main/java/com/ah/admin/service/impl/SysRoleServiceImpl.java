package com.ah.admin.service.impl;

import com.ah.admin.domain.SysRole;
import com.ah.admin.mapper.SysRoleMapper;
import com.ah.admin.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:54
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

}

