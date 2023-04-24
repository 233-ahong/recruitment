package com.ah.admin.controller;


import com.ah.admin.domain.RecruitmentInfo;
import com.ah.admin.service.IRecruitmentInfoService;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 招聘简章表(RecruitmentInfo)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:47
 */
@RestController
@RequestMapping("/recruitmentInfo")
public class RecruitmentInfoController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private IRecruitmentInfoService recruitmentInfoService;

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param recruitmentInfo 查询实体
     * @return 所有数据
     */
    @GetMapping
    public AjaxResult selectAll(Page<RecruitmentInfo> page, RecruitmentInfo recruitmentInfo) {
        return AjaxResult.success(recruitmentInfoService.page(page, new QueryWrapper<>(recruitmentInfo)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(recruitmentInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param recruitmentInfo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody RecruitmentInfo recruitmentInfo) {
        return AjaxResult.success(recruitmentInfoService.save(recruitmentInfo));
    }

    /**
     * 修改数据
     *
     * @param recruitmentInfo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody RecruitmentInfo recruitmentInfo) {
        return AjaxResult.success(recruitmentInfoService.updateById(recruitmentInfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public AjaxResult delete(@RequestParam("idList") List<Long> idList) {
        return AjaxResult.success(recruitmentInfoService.removeByIds(idList));
    }
}

