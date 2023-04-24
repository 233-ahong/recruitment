package com.ah.admin.service.impl;

import com.ah.admin.domain.MajorCategory;
import com.ah.admin.mapper.MajorCategoryMapper;
import com.ah.admin.service.IMajorCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 专业类别表(MajorCategory)表服务实现类
 *
 * @author 26471
 * @since 2023-04-20 22:35:15
 */
@Service("majorCategoryService")
public class MajorCategoryServiceImpl extends ServiceImpl<MajorCategoryMapper, MajorCategory> implements IMajorCategoryService {

}

