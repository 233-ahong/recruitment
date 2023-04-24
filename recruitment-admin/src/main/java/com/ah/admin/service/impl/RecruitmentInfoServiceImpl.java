package com.ah.admin.service.impl;

import com.ah.admin.domain.RecruitmentInfo;
import com.ah.admin.mapper.RecruitmentInfoMapper;
import com.ah.admin.service.IRecruitmentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 招聘简章表(RecruitmentInfo)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:48
 */
@Service("recruitmentInfoService")
public class RecruitmentInfoServiceImpl extends ServiceImpl<RecruitmentInfoMapper, RecruitmentInfo> implements IRecruitmentInfoService {

}

