package com.ah.admin.service.impl;

import com.ah.admin.domain.Honor;
import com.ah.admin.mapper.HonorMapper;
import com.ah.admin.service.IHonorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 荣誉奖励表(Honor)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:43
 */
@Service("honorService")
public class HonorServiceImpl extends ServiceImpl<HonorMapper, Honor> implements IHonorService {

    @Resource
    private HonorMapper honorMapper;

    @Override
    public List<Honor> getHonorByResumeId(Integer id) {
        return honorMapper.getHonorByResumeId(id);
    }
}

