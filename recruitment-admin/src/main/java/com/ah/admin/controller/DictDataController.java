package com.ah.admin.controller;

import com.ah.admin.service.IDictDataService;
import com.ah.admin.service.IDictTypeService;
import com.ah.api.domain.DictData;
import com.ah.common.utils.SecurityUtils;
import com.ah.common.utils.StringUtils;
import com.ah.common.utils.web.AjaxResult;
import com.ah.common.utils.web.BaseController;
import com.ah.common.utils.web.page.TableDataInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 26471
 * @date 2023/4/9 10:48
 * @description
 */
@RestController
@RequestMapping("/sys/dictDate")
public class DictDataController extends BaseController {
    @Resource
    private IDictDataService dictDataService;

    @Resource
    private IDictTypeService dictTypeService;

    @GetMapping("/list")
    public TableDataInfo list(DictData dictData) {
        startPage();
        List<DictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }


    /**
     * 查询字典数据详细
     */
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable Long dictCode) {
        return AjaxResult.success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType) {
        List<DictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data)) {
            data = new ArrayList<DictData>();
        }
        return AjaxResult.success(data);
    }

    /**
     * 新增字典类型
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody DictData dict) {
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody DictData dict) {
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
        return success();
    }
}

