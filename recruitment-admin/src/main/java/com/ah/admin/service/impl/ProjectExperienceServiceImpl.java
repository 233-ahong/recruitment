package com.ah.admin.service.impl;

import com.ah.admin.domain.ProjectExperience;
import com.ah.admin.mapper.ProjectExperienceMapper;
import com.ah.admin.service.IProjectExperienceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 比赛/项目经历表(ProjectExperience)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:46
 */
@Service("projectExperienceService")
public class ProjectExperienceServiceImpl extends ServiceImpl<ProjectExperienceMapper, ProjectExperience> implements IProjectExperienceService {

    @Resource
    private ProjectExperienceMapper projectExperienceMapper;

    @Override
    public List<ProjectExperience> getProjectExperienceByResumeId(Integer id) {
        return projectExperienceMapper.getProjectExperienceByResumeId(id);
    }
}

