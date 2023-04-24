package com.ah.admin.controller;


import com.ah.admin.domain.Resumes;
import com.ah.admin.domain.WorkExperience;
import com.ah.admin.service.IResumesService;
import com.ah.admin.service.IWorkExperienceService;
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
 * 工作/实习经历表(WorkExperience)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:56
 */
@RestController
@RequestMapping("/workExperience")
public class WorkExperienceController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private IWorkExperienceService workExperienceService;

    @Resource
    private IResumesService resumesService;

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param workExperience 查询实体
     * @return 所有数据
     */
    @GetMapping
    public AjaxResult selectAll(Page<WorkExperience> page, WorkExperience workExperience) {
        return AjaxResult.success(workExperienceService.page(page, new QueryWrapper<>(workExperience)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(workExperienceService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param workExperience 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody WorkExperience workExperience) {
        Long userId = SecurityUtils.getUserId();
        Resumes byUserId = resumesService.getByUserId(userId);
        workExperience.setResumeId(byUserId.getId());
        workExperience.setUserId(Math.toIntExact(userId));
        return AjaxResult.success(workExperienceService.save(workExperience));
    }

    /**
     * 修改数据
     *
     * @param workExperience 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody WorkExperience workExperience) {
        return AjaxResult.success(workExperienceService.updateById(workExperience));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(workExperienceService.removeByIds(idList));
    }
}

