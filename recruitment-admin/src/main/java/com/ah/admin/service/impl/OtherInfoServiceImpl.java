package com.ah.admin.service.impl;

import com.ah.admin.domain.OtherInfo;
import com.ah.admin.mapper.OtherInfoMapper;
import com.ah.admin.service.IOtherInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 其他信息表(OtherInfo)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:45
 */
@Service("otherInfoService")
public class OtherInfoServiceImpl extends ServiceImpl<OtherInfoMapper, OtherInfo> implements IOtherInfoService {

    @Resource
    private OtherInfoMapper otherInfoMapper;

    @Override
    public List<OtherInfo> getOtherInfoByResumeId(Integer id) {
        return otherInfoMapper.getOtherInfoByResumeId(id);
    }
}

