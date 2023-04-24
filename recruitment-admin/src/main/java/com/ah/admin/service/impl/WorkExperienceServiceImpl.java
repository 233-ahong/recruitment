package com.ah.admin.service.impl;

import com.ah.admin.domain.WorkExperience;
import com.ah.admin.mapper.WorkExperienceMapper;
import com.ah.admin.service.IWorkExperienceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 工作/实习经历表(WorkExperience)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:57
 */
@Service("workExperienceService")
public class WorkExperienceServiceImpl extends ServiceImpl<WorkExperienceMapper, WorkExperience> implements IWorkExperienceService {

    @Resource
    private WorkExperienceMapper workExperienceMapper;

    @Override
    public List<WorkExperience> getWorkExperienceByResumeId(Integer id) {
        return workExperienceMapper.getWorkExperienceByResumeId(id);
    }
}

