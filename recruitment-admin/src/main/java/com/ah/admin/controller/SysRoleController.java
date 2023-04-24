package com.ah.admin.controller;


import com.ah.admin.domain.SysRole;
import com.ah.admin.service.ISysRoleService;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 角色信息表(SysRole)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:53
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ISysRoleService sysRoleService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param sysRole 查询实体
     * @return 所有数据
     */
    @GetMapping
    public AjaxResult selectAll(Page<SysRole> page, SysRole sysRole) {
        return AjaxResult.success(sysRoleService.page(page, new QueryWrapper<>(sysRole)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(sysRoleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysRole 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody SysRole sysRole) {
        return AjaxResult.success(sysRoleService.save(sysRole));
    }

    /**
     * 修改数据
     *
     * @param sysRole 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody SysRole sysRole) {
        return AjaxResult.success(sysRoleService.updateById(sysRole));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(sysRoleService.removeByIds(idList));
    }
}

