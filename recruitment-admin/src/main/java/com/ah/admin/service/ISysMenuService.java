package com.ah.admin.service;

import com.ah.admin.domain.SysMenu;
import com.ah.admin.domain.vo.RouterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 菜单权限表(SysMenu)表服务接口
 *
 * @author 26471
 * @since 2023-04-15 20:41:53
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<SysMenu> selectMenuTreeByUserId(Long userId);

    public List<RouterVo> buildMenus(List<SysMenu> menus);

    List<SysMenu> selectList(SysMenu sysMenu);
}

