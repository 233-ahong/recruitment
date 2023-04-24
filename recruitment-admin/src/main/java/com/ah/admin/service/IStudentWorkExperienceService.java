package com.ah.admin.service;

import com.ah.admin.domain.StudentWorkExperience;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 学生工作经历表(StudentWorkExperience)表服务接口
 *
 * @author 26471
 * @since 2023-04-15 20:41:50
 */
public interface IStudentWorkExperienceService extends IService<StudentWorkExperience> {

    /**
     * 通过简历id查询工作经历
     *
     * @param id 简历id
     * @return 工作经历
     */
    List<StudentWorkExperience> getStudentWorkExperienceByResumeId(Integer id);
}

