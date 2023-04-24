package com.ah.admin.service.impl;

import com.ah.admin.domain.JobOpenings;
import com.ah.admin.mapper.JobOpeningsMapper;
import com.ah.admin.service.IJobOpeningsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 招聘职位表(JobOpenings)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:44
 */
@Service("jobOpeningsService")
public class JobOpeningsServiceImpl extends ServiceImpl<JobOpeningsMapper, JobOpenings> implements IJobOpeningsService {

}

