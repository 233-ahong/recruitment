package com.ah.admin.service;

import com.ah.api.domain.SysDictData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 字典数据表(SysDictData)表服务接口
 *
 * @author 26471
 * @since 2023-04-15 20:41:51
 */
public interface ISysDictDataService extends IService<SysDictData> {

    List<SysDictData> selectDictDataByType(String dictType);

}

