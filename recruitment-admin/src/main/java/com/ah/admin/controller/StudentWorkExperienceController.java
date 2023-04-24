package com.ah.admin.controller;


import com.ah.admin.domain.StudentWorkExperience;
import com.ah.admin.service.IStudentWorkExperienceService;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 学生工作经历表(StudentWorkExperience)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:50
 */
@RestController
@RequestMapping("/studentWorkExperience")
public class StudentWorkExperienceController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private IStudentWorkExperienceService studentWorkExperienceService;

    /**
     * 分页查询所有数据
     *
     * @param page                  分页对象
     * @param studentWorkExperience 查询实体
     * @return 所有数据
     */
    @GetMapping
    public AjaxResult selectAll(Page<StudentWorkExperience> page, StudentWorkExperience studentWorkExperience) {
        return AjaxResult.success(studentWorkExperienceService.page(page, new QueryWrapper<>(studentWorkExperience)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(studentWorkExperienceService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param studentWorkExperience 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody StudentWorkExperience studentWorkExperience) {
        return AjaxResult.success(studentWorkExperienceService.save(studentWorkExperience));
    }

    /**
     * 修改数据
     *
     * @param studentWorkExperience 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody StudentWorkExperience studentWorkExperience) {
        return AjaxResult.success(studentWorkExperienceService.updateById(studentWorkExperience));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(studentWorkExperienceService.removeByIds(idList));
    }
}

