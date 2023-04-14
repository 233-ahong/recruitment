package com.ah.auth.service;

import com.ah.api.domain.LoginUser;
import com.ah.api.domain.R;
import com.ah.api.domain.SysUser;
import com.ah.api.remote.RemoteUserService;
import com.ah.common.constant.SecurityConstants;
import com.ah.common.exception.ServiceException;
import com.ah.common.utils.SecurityUtils;
import com.ah.common.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 26471
 * @date 2023/4/4 13:36
 * @description
 */
@Component
public class LoginService {

    @Resource
    private RemoteUserService remoteUserService;


    public LoginUser login(String userName, String password) {
        if (StringUtils.isAllBlank(userName, password)) {
            throw new ServiceException("用户名/密码不能为空");
        }
        R<LoginUser> userResult = remoteUserService.getUserInfoByName(userName, SecurityConstants.INNER);
        if (R.FAIL == userResult.getCode()) {
            throw new ServiceException(userResult.getMsg());
        }
        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData())) {
            throw new ServiceException("登录用户：" + userName + " 不存在");
        }
        LoginUser userInfo = userResult.getData();
        SysUser user = userResult.getData().getSysUser();
        if ("2".equals(user.getDelFlag())) {
            throw new ServiceException("对不起，您的账号：" + userName + " 已被删除");
        }
        if ("1".equals(user.getStatus())) {
            throw new ServiceException("对不起，您的账号：" + userName + " 已停用");
        }
        if (!SecurityUtils.matchesPassword(password, user.getPassword())) {
            throw new ServiceException("用户不存在/密码错误");
        }
        return userInfo;
    }

    public LoginUser loginByPhone(String phone) {
        R<LoginUser> userResult = remoteUserService.getUserInfoByPhone(phone, SecurityConstants.INNER);
        if (R.FAIL == userResult.getCode()) {
            throw new ServiceException(userResult.getMsg());
        }
        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData())) {
            throw new ServiceException("登录用户：" + phone + " 不存在");
        }
        LoginUser userInfo = userResult.getData();
        SysUser user = userResult.getData().getSysUser();
        if ("2".equals(user.getDelFlag())) {
            throw new ServiceException("对不起，您的账号：" + phone + " 已被删除");
        }
        if ("1".equals(user.getStatus())) {
            throw new ServiceException("对不起，您的账号：" + phone + " 已停用");
        }
        return userInfo;
    }
}
