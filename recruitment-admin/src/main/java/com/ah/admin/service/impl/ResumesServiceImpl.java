package com.ah.admin.service.impl;

import com.ah.admin.domain.*;
import com.ah.admin.mapper.ResumesMapper;
import com.ah.admin.service.*;
import com.ah.common.utils.StringUtils;
import com.ah.common.utils.web.AjaxResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 简历表(Resumes)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:49
 */
@Service("resumesService")
public class ResumesServiceImpl extends ServiceImpl<ResumesMapper, Resumes> implements IResumesService {

    @Resource
    private ResumesMapper resumesMapper;

    @Resource
    private IWorkExperienceService workExperienceService;

    @Resource
    private IProjectExperienceService projectExperienceService;

    @Resource
    private IEducationBackgroundService educationBackgroundService;

    @Resource
    private IHonorService honorService;

    @Resource
    private IOtherInfoService otherInfoService;

    @Resource
    private IStudentWorkExperienceService studentWorkExperienceService;

    @Override
    public Resumes getByUserId(Long userId) {
        return resumesMapper.getByUserId(userId);
    }

    @Override
    public AjaxResult getResumeInfo(Long userId) {
        AjaxResult ajax = AjaxResult.success();
        Resumes resumes = getByUserId(userId);
        if (StringUtils.isNull(resumes)) {
            return AjaxResult.success("请先完善简历信息");
        }
        List<EducationBackground> educationBackground = educationBackgroundService.getEducationBackgroundByResumeId(resumes.getId());
        List<WorkExperience> workExperience = workExperienceService.getWorkExperienceByResumeId(resumes.getId());
        List<ProjectExperience> projectExperience = projectExperienceService.getProjectExperienceByResumeId(resumes.getId());
        List<Honor> honor = honorService.getHonorByResumeId(resumes.getId());
        List<OtherInfo> otherInfo = otherInfoService.getOtherInfoByResumeId(resumes.getId());
        List<StudentWorkExperience> studentWorkExperience = studentWorkExperienceService.getStudentWorkExperienceByResumeId(resumes.getId());
        ajax.put("resumes", resumes);
        ajax.put("educationBackground", educationBackground);
        ajax.put("workExperience", workExperience);
        ajax.put("projectExperience", projectExperience);
        ajax.put("honor", honor);
        ajax.put("otherInfo", otherInfo);
        ajax.put("studentWorkExperience", studentWorkExperience);
        return ajax;
    }
}

