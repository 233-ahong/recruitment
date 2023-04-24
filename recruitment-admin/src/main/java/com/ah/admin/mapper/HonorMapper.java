package com.ah.admin.mapper;

import com.ah.admin.domain.Honor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 荣誉奖励表(Honor)表数据库访问层
 *
 * @author 26471
 * @since 2023-04-15 20:41:42
 */
public interface HonorMapper extends BaseMapper<Honor> {

    /**
     * 通过简历id查询荣誉奖励
     *
     * @param id 简历id
     * @return 荣誉奖励
     */
    List<Honor> getHonorByResumeId(Integer id);
}

