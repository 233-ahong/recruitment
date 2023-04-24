package com.ah.admin.controller;


import com.ah.admin.domain.Resumes;
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
 * 简历表(Resumes)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:48
 */
@RestController
@RequestMapping("/resumes")
public class ResumesController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private IResumesService resumesService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param resumes 查询实体
     * @return 所有数据
     */
    @GetMapping
    public AjaxResult selectAll(Page<Resumes> page, Resumes resumes) {
        return AjaxResult.success(resumesService.page(page, new QueryWrapper<>(resumes)));
    }

    /**
     * 获取简历信息
     */
    @GetMapping("/getResumeInfo")
    public AjaxResult getResumeInfo() {
        Long userId = SecurityUtils.getUserId();
        System.out.println(userId);
        return resumesService.getResumeInfo(userId);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(resumesService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param resumes 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody Resumes resumes) {
        resumes.setUserId(SecurityUtils.getUserId().intValue());
        return AjaxResult.success(resumesService.save(resumes));
    }

    /**
     * 修改数据
     *
     * @param resumes 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody Resumes resumes) {
        return AjaxResult.success(resumesService.updateById(resumes));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(resumesService.removeByIds(idList));
    }
}

