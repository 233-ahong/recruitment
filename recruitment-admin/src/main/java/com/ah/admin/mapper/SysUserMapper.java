package com.ah.admin.mapper;

import com.ah.api.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息表(SysUser)表数据库访问层
 *
 * @author 26471
 * @since 2023-04-15 20:41:55
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectUserByUserName(@Param("username") String username);

    SysUser selectUserByPhone(String phone);
}

