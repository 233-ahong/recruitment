package com.ah.admin.controller;


import com.ah.admin.domain.EducationBackground;
import com.ah.admin.domain.Resumes;
import com.ah.admin.service.IEducationBackgroundService;
import com.ah.admin.service.IResumesService;
import com.ah.common.utils.SecurityUtils;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 教育背景表(EducationBackground)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:40
 */
@RestController
@RequestMapping("/educationBackground")
public class EducationBackgroundController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private IEducationBackgroundService educationBackgroundService;
    @Resource
    private IResumesService resumesService;

    /**
     * 分页查询所有数据
     *
     * @param page                分页对象
     * @param educationBackground 查询实体
     * @return 所有数据
     */
    @GetMapping
    public AjaxResult selectAll(Page<EducationBackground> page, EducationBackground educationBackground) {
        return AjaxResult.success(educationBackgroundService.page(page, new QueryWrapper<>(educationBackground)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(educationBackgroundService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param educationBackground 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody EducationBackground educationBackground) {
        Long userId = SecurityUtils.getUserId();
        Resumes byUserId = resumesService.getByUserId(userId);
        educationBackground.setUserId(userId);
        educationBackground.setResumeId(byUserId.getId());
        return AjaxResult.success(educationBackgroundService.save(educationBackground));
    }

    /**
     * 修改数据
     *
     * @param educationBackground 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody EducationBackground educationBackground) {
        return AjaxResult.success(educationBackgroundService.updateById(educationBackground));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(educationBackgroundService.removeByIds(idList));
    }
}

