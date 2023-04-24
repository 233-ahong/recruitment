package com.ah.admin.controller;


import com.ah.admin.domain.ProjectExperience;
import com.ah.admin.service.IProjectExperienceService;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 比赛/项目经历表(ProjectExperience)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:45
 */
@RestController
@RequestMapping("/projectExperience")
public class ProjectExperienceController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private IProjectExperienceService projectExperienceService;

    /**
     * 分页查询所有数据
     *
     * @param page              分页对象
     * @param projectExperience 查询实体
     * @return 所有数据
     */
    @GetMapping
    public AjaxResult selectAll(Page<ProjectExperience> page, ProjectExperience projectExperience) {
        return AjaxResult.success(projectExperienceService.page(page, new QueryWrapper<>(projectExperience)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(projectExperienceService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param projectExperience 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody ProjectExperience projectExperience) {
        return AjaxResult.success(projectExperienceService.save(projectExperience));
    }

    /**
     * 修改数据
     *
     * @param projectExperience 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody ProjectExperience projectExperience) {
        return AjaxResult.success(projectExperienceService.updateById(projectExperience));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(projectExperienceService.removeByIds(idList));
    }
}

