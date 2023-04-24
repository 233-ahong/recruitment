package com.ah.admin.controller;


import com.ah.admin.domain.SysUserRole;
import com.ah.admin.service.ISysUserRoleService;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户和角色关联表(SysUserRole)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:55
 */
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ISysUserRoleService sysUserRoleService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param sysUserRole 查询实体
     * @return 所有数据
     */
    @GetMapping
    public AjaxResult selectAll(Page<SysUserRole> page, SysUserRole sysUserRole) {
        return AjaxResult.success(sysUserRoleService.page(page, new QueryWrapper<>(sysUserRole)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(sysUserRoleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysUserRole 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody SysUserRole sysUserRole) {
        return AjaxResult.success(sysUserRoleService.save(sysUserRole));
    }

    /**
     * 修改数据
     *
     * @param sysUserRole 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody SysUserRole sysUserRole) {
        return AjaxResult.success(sysUserRoleService.updateById(sysUserRole));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(sysUserRoleService.removeByIds(idList));
    }
}

