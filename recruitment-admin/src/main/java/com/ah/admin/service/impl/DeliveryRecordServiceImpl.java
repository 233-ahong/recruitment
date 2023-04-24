package com.ah.admin.service.impl;

import com.ah.admin.domain.DeliveryRecord;
import com.ah.admin.mapper.DeliveryRecordMapper;
import com.ah.admin.service.IDeliveryRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 投递记录表(DeliveryRecord)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:40
 */
@Service("deliveryRecordService")
public class DeliveryRecordServiceImpl extends ServiceImpl<DeliveryRecordMapper, DeliveryRecord> implements IDeliveryRecordService {

}

