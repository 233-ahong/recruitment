package com.ah.admin.mapper;

import com.ah.admin.domain.StudentWorkExperience;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 学生工作经历表(StudentWorkExperience)表数据库访问层
 *
 * @author 26471
 * @since 2023-04-15 20:41:50
 */
public interface StudentWorkExperienceMapper extends BaseMapper<StudentWorkExperience> {

    /**
     * 通过简历id查询工作经历
     *
     * @param id 简历id
     * @return 工作经历
     */
    List<StudentWorkExperience> getStudentWorkExperienceByResumeId(Integer id);
}

