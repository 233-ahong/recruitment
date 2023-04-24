package com.ah.admin.service.impl;

import com.ah.admin.domain.SysDictType;
import com.ah.admin.mapper.SysDictTypeMapper;
import com.ah.admin.service.ISysDictTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 字典类型表(SysDictType)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:52
 */
@Service("sysDictTypeService")
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements ISysDictTypeService {

}

