package com.ah.admin.controller;


import com.ah.admin.domain.JobOpenings;
import com.ah.admin.service.IJobOpeningsService;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 招聘职位表(JobOpenings)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:43
 */
@RestController
@RequestMapping("/jobOpenings")
public class JobOpeningsController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private IJobOpeningsService jobOpeningsService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param jobOpenings 查询实体
     * @return 所有数据
     */
    @GetMapping
    public AjaxResult selectAll(Page<JobOpenings> page, JobOpenings jobOpenings) {
        return AjaxResult.success(jobOpeningsService.page(page, new QueryWrapper<>(jobOpenings)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(jobOpeningsService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param jobOpenings 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody JobOpenings jobOpenings) {
        return AjaxResult.success(jobOpeningsService.save(jobOpenings));
    }

    /**
     * 修改数据
     *
     * @param jobOpenings 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody JobOpenings jobOpenings) {
        return AjaxResult.success(jobOpeningsService.updateById(jobOpenings));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(jobOpeningsService.removeByIds(idList));
    }
}

