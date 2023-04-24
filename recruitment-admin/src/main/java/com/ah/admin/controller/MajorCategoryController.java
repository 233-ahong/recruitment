package com.ah.admin.controller;


import com.ah.admin.domain.MajorCategory;
import com.ah.admin.service.IMajorCategoryService;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 专业类别表(MajorCategory)表控制层
 *
 * @author 26471
 * @since 2023-04-20 22:35:15
 */
@RestController
@RequestMapping("/majorCategory")
public class MajorCategoryController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private IMajorCategoryService majorCategoryService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param majorCategory 查询实体
     * @return 所有数据
     */
    @GetMapping
    public AjaxResult selectAll(Page<MajorCategory> page, MajorCategory majorCategory) {
        return AjaxResult.success(majorCategoryService.page(page, new QueryWrapper<>(majorCategory)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(majorCategoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param majorCategory 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody MajorCategory majorCategory) {
        return AjaxResult.success(majorCategoryService.save(majorCategory));
    }

    /**
     * 修改数据
     *
     * @param majorCategory 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody MajorCategory majorCategory) {
        return AjaxResult.success(majorCategoryService.updateById(majorCategory));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(majorCategoryService.removeByIds(idList));
    }
}

