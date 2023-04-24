package com.ah.admin.service.impl;

import com.ah.admin.mapper.SysDictDataMapper;
import com.ah.admin.service.ISysDictDataService;
import com.ah.api.domain.SysDictData;
import com.ah.common.utils.DictUtils;
import com.ah.common.utils.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典数据表(SysDictData)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:51
 */
@Service("sysDictDataService")
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements ISysDictDataService {

    @Resource
    SysDictDataMapper sysDictDataMapper;

    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        List<SysDictData> sysDictData = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotEmpty(sysDictData)) {
            return sysDictData;
        }
        sysDictData = sysDictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(sysDictData)) {
            DictUtils.setDictCache(dictType, sysDictData);
            return sysDictData;
        }
        return null;
    }
}

