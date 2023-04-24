package com.ah.admin.mapper;

import com.ah.admin.domain.Resumes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 简历表(Resumes)表数据库访问层
 *
 * @author 26471
 * @since 2023-04-15 20:41:48
 */
public interface ResumesMapper extends BaseMapper<Resumes> {

    /**
     * 通过用户id查询简历信息
     *
     * @param userId userId
     * @return Resumes
     */
    Resumes getByUserId(Long userId);
}

