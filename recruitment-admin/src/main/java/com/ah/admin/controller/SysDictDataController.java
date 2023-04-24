package com.ah.admin.controller;


import com.ah.admin.service.ISysDictDataService;
import com.ah.api.domain.SysDictData;
import com.ah.common.utils.StringUtils;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典数据表(SysDictData)表控制层
 *
 * @author 26471
 * @since 2023-04-15 20:41:51
 */
@RestController
@RequestMapping("/sys/dictData")
public class SysDictDataController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ISysDictDataService sysDictDataService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param sysDictData 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public AjaxResult selectAll(Page<SysDictData> page, SysDictData sysDictData) {
        return AjaxResult.success(sysDictDataService.page(page, new QueryWrapper<>(sysDictData)));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType) {
        List<SysDictData> data = sysDictDataService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data)) {
            data = new ArrayList<SysDictData>();
        }
        return AjaxResult.success(data);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/dict/{id}")
    public AjaxResult selectOne(@PathVariable Serializable id) {
        return AjaxResult.success(sysDictDataService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysDictData 实体对象
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult insert(@RequestBody SysDictData sysDictData) {
        return AjaxResult.success(sysDictDataService.save(sysDictData));
    }

    /**
     * 修改数据
     *
     * @param sysDictData 实体对象
     * @return 修改结果
     */
    @PutMapping
    public AjaxResult update(@RequestBody SysDictData sysDictData) {
        return AjaxResult.success(sysDictDataService.updateById(sysDictData));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/{idList}")
    public AjaxResult delete(@PathVariable("idList") List<Long> idList) {
        return AjaxResult.success(sysDictDataService.removeByIds(idList));
    }
}

