package com.ah.admin.service.impl;

import com.ah.admin.domain.EducationBackground;
import com.ah.admin.mapper.EducationBackgroundMapper;
import com.ah.admin.service.IEducationBackgroundService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教育背景表(EducationBackground)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:41
 */
@Service("educationBackgroundService")
public class EducationBackgroundServiceImpl extends ServiceImpl<EducationBackgroundMapper, EducationBackground> implements IEducationBackgroundService {

    @Resource
    private EducationBackgroundMapper educationBackgroundMapper;

    @Override
    public List<EducationBackground> getEducationBackgroundByResumeId(Integer id) {
        return educationBackgroundMapper.getEducationBackgroundByResumeId(id);
    }
}

