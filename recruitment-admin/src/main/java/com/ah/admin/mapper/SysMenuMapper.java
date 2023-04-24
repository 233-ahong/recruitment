package com.ah.admin.mapper;

import com.ah.admin.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 菜单权限表(SysMenu)表数据库访问层
 *
 * @author 26471
 * @since 2023-04-15 20:41:53
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 查询所有
     *
     * @return 所有菜单
     */
    List<SysMenu> selectMenuTreeAll();

    /**
     * 查询特定的菜单
     *
     * @param sysMenu 菜单
     * @return 返回特定的菜单
     */
    List<SysMenu> selectByList(SysMenu sysMenu);
}

