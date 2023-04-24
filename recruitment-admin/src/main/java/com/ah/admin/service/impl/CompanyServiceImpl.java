package com.ah.admin.service.impl;

import com.ah.admin.domain.Company;
import com.ah.admin.mapper.CompanyMapper;
import com.ah.admin.service.ICompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 公司表(Company)表服务实现类
 *
 * @author 26471
 * @since 2023-04-15 20:41:39
 */
@Service("companyService")
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

}

