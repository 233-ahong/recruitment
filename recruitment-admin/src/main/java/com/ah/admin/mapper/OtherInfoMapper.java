package com.ah.admin.mapper;

import com.ah.admin.domain.OtherInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 其他信息表(OtherInfo)表数据库访问层
 *
 * @author 26471
 * @since 2023-04-15 20:41:44
 */
public interface OtherInfoMapper extends BaseMapper<OtherInfo> {

    /**
     * 通过简历id查询其他信息
     *
     * @param id 简历id
     * @return 其他信息
     */
    List<OtherInfo> getOtherInfoByResumeId(Integer id);
}

