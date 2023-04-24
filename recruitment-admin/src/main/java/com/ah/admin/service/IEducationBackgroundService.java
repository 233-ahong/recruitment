package com.ah.admin.service;

import com.ah.admin.domain.EducationBackground;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 教育背景表(EducationBackground)表服务接口
 *
 * @author 26471
 * @since 2023-04-15 20:41:41
 */
public interface IEducationBackgroundService extends IService<EducationBackground> {

    /**
     * 通过简历id查询教育背景信息
     *
     * @param id 简历id
     * @return 教育背景信息
     */
    List<EducationBackground> getEducationBackgroundByResumeId(Integer id);
}

