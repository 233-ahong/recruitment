package com.ah.admin.mapper;

import com.ah.admin.domain.ProjectExperience;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 比赛/项目经历表(ProjectExperience)表数据库访问层
 *
 * @author 26471
 * @since 2023-04-15 20:41:46
 */
public interface ProjectExperienceMapper extends BaseMapper<ProjectExperience> {

    /**
     * 通过简历id查询比赛/项目经历
     *
     * @param id 简历id
     * @return 比赛/项目经历
     */
    List<ProjectExperience> getProjectExperienceByResumeId(Integer id);
}

