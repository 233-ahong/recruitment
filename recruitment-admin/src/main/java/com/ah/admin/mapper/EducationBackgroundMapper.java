package com.ah.admin.mapper;

import com.ah.admin.domain.EducationBackground;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 教育背景表(EducationBackground)表数据库访问层
 *
 * @author 26471
 * @since 2023-04-15 20:41:41
 */
public interface EducationBackgroundMapper extends BaseMapper<EducationBackground> {

    /**
     * 通过简历id查询教育背景信息
     *
     * @param id 简历id
     * @return 教育背景信息
     */
    List<EducationBackground> getEducationBackgroundByResumeId(Integer id);
}

