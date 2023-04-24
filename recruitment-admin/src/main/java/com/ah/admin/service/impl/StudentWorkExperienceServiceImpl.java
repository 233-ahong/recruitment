package com.ah.admin.service.impl;

import com.ah.admin.domain.StudentWorkExperience;
import com.ah.admin.mapper.StudentWorkExperienceMapper;
import com.ah.admin.service.IStudentWorkExperienceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生工作经历表(StudentWorkExperience)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:50
 */
@Service("studentWorkExperienceService")
public class StudentWorkExperienceServiceImpl extends ServiceImpl<StudentWorkExperienceMapper, StudentWorkExperience> implements IStudentWorkExperienceService {

    @Resource
    private StudentWorkExperienceMapper studentWorkExperienceMapper;

    @Override
    public List<StudentWorkExperience> getStudentWorkExperienceByResumeId(Integer id) {
        return studentWorkExperienceMapper.getStudentWorkExperienceByResumeId(id);
    }
}

