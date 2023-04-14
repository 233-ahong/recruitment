package com.ah.admin.controller;

import com.ah.admin.service.IDictTypeService;
import com.ah.api.domain.DictType;
import com.ah.common.constant.UserConstants;
import com.ah.common.utils.SecurityUtils;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.ah.common.utils.web.page.TableDataInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 26471
 * @date 2023/4/9 12:22
 * @description
 */
@RestController
@RequestMapping("/sys/dictType")
public class DictTypeController extends BaseController {
    @Resource
    private IDictTypeService dictTypeService;

    @GetMapping("/list")
    public TableDataInfo list(DictType dictType) {
        startPage();
        List<DictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    /**
     * 查询字典类型详细
     */
    @GetMapping(value = "/{dictId}")
    public AjaxResult getInfo(@PathVariable Long dictId) {
        return AjaxResult.success(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 新增字典类型
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody DictType dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return AjaxResult.error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody DictType dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return AjaxResult.error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictTypeService.updateDictType(dict));
    }

    /**
     * 删除字典类型
     */
    @DeleteMapping("/{dictIds}")
    public AjaxResult remove(@PathVariable Long[] dictIds) {
        dictTypeService.deleteDictTypeByIds(dictIds);
        return success();
    }

    /**
     * 刷新字典缓存
     */
    @DeleteMapping("/refreshCache")
    public AjaxResult refreshCache() {
        dictTypeService.resetDictCache();
        return AjaxResult.success();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect() {
        List<DictType> dictTypes = dictTypeService.selectDictTypeAll();
        return AjaxResult.success(dictTypes);
    }
}

