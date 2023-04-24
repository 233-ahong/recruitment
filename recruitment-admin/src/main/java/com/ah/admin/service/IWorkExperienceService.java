package com.ah.admin.service;

import com.ah.admin.domain.WorkExperience;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 工作/实习经历表(WorkExperience)表服务接口
 *
 * @author 26471
 * @since 2023-04-15 20:41:57
 */
public interface IWorkExperienceService extends IService<WorkExperience> {

    /**
     * 通过简历id查询工作/实习经历信息
     *
     * @param id 简历id
     * @return 工作/实习经历信息
     */
    List<WorkExperience> getWorkExperienceByResumeId(Integer id);
}

