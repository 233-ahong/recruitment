package com.ah.admin.service;

import com.ah.admin.domain.OtherInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 其他信息表(OtherInfo)表服务接口
 *
 * @author 26471
 * @since 2023-04-15 20:41:45
 */
public interface IOtherInfoService extends IService<OtherInfo> {

    /**
     * 通过简历id查询其他信息
     *
     * @param id 简历id
     * @return 其他信息
     */
    List<OtherInfo> getOtherInfoByResumeId(Integer id);
}

