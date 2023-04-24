package com.ah.admin.mapper;

import com.ah.admin.domain.WorkExperience;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 工作/实习经历表(WorkExperience)表数据库访问层
 *
 * @author 26471
 * @since 2023-04-15 20:41:56
 */
public interface WorkExperienceMapper extends BaseMapper<WorkExperience> {

    /**
     * 通过简历id查询工作/实习经历信息
     *
     * @param id 简历id
     * @return 工作/实习经历信息
     */
    List<WorkExperience> getWorkExperienceByResumeId(Integer id);
}

