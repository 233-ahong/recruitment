package com.ah.admin.service;

import com.ah.admin.domain.Resumes;
import com.ah.common.utils.web.AjaxResult;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 简历表(Resumes)表服务接口
 *
 * @author 26471
 * @since 2023-04-15 20:41:49
 */
public interface IResumesService extends IService<Resumes> {

    /**
     * 通过用户id查询简历信息
     *
     * @param userId 用户id
     * @return 简历信息
     */
    Resumes getByUserId(Long userId);

    /**
     * 获取简历信息
     *
     * @param userId 用户id
     * @return 简历信息
     */
    AjaxResult getResumeInfo(Long userId);
}

