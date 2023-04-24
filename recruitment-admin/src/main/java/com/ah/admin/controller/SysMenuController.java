package com.ah.admin.controller;


import com.ah.admin.domain.SysMenu;
import com.ah.admin.domain.vo.RouterVo;
import com.ah.admin.service.ISysMenuService;
import com.ah.common.constant.UserConstants;
import com.ah.common.redis.service.RedisService;
import com.ah.common.utils.SecurityUtils;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单权限表(SysMenu)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:53
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ISysMenuService sysMenuService;

    @Resource
    private RedisService redisService;

    /**
     * 分页查询所有数据
     *
     * @param sysMenu 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public AjaxResult selectAll(SysMenu sysMenu) {
        return AjaxResult.success(sysMenuService.selectList(sysMenu));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/id/{id}")
    public AjaxResult selectOne(@PathVariable("id") Serializable id) {
        return AjaxResult.success(sysMenuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody SysMenu sysMenu) {
        return AjaxResult.success(sysMenuService.save(sysMenu));
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody SysMenu sysMenu) {
        return AjaxResult.success(sysMenuService.updateById(sysMenu));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(sysMenuService.removeByIds(idList));
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public AjaxResult getRouters() {
        List<RouterVo> cacheList = redisService.getCacheList(UserConstants.MENU_LIST);
        if (!cacheList.isEmpty()) {
            return AjaxResult.success(cacheList);
        }
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = sysMenuService.selectMenuTreeByUserId(userId);
        List<RouterVo> routerVos = sysMenuService.buildMenus(menus);
        redisService.setCacheList(UserConstants.MENU_LIST, routerVos);
        return AjaxResult.success(routerVos);
    }
}

