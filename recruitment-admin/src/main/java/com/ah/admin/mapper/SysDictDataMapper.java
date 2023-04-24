package com.ah.admin.mapper;

import com.ah.api.domain.SysDictData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 字典数据表(SysDictData)表数据库访问层
 *
 * @author 26471
 * @since 2023-04-15 20:41:51
 */
public interface SysDictDataMapper extends BaseMapper<SysDictData> {
    List<SysDictData> selectDictDataByType(String dictType);
}

