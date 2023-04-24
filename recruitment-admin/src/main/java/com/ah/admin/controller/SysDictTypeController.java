package com.ah.admin.controller;


import com.ah.admin.domain.SysDictType;
import com.ah.admin.service.ISysDictTypeService;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 字典类型表(SysDictType)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:52
 */
@RestController
@RequestMapping("/sys/dictType")
public class SysDictTypeController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ISysDictTypeService sysDictTypeService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param sysDictType 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public AjaxResult selectAll(Page<SysDictType> page, SysDictType sysDictType) {
        return AjaxResult.success(sysDictTypeService.page(page, new QueryWrapper<>(sysDictType)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(sysDictTypeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysDictType 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody SysDictType sysDictType) {
        return AjaxResult.success(sysDictTypeService.save(sysDictType));
    }

    /**
     * 修改数据
     *
     * @param sysDictType 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody SysDictType sysDictType) {
        return AjaxResult.success(sysDictTypeService.updateById(sysDictType));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(sysDictTypeService.removeByIds(idList));
    }
}

