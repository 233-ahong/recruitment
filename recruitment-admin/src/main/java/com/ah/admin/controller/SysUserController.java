package com.ah.admin.controller;

import com.ah.admin.service.ISysUserService;
import com.ah.api.domain.LoginUser;
import com.ah.api.domain.R;
import com.ah.api.domain.SysUser;
import com.ah.common.annotation.InnerAuth;
import com.ah.common.utils.StringUtils;
import com.ah.common.utils.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 26471
 * @date 2023/4/4 17:26
 * @description
 */
@RestController
@RequestMapping("/sys/use")
public class SysUserController extends BaseController {

    @Resource
    private ISysUserService userService;

    @InnerAuth
    @GetMapping("/infoByName/{username}")
    public R<LoginUser> infoByName(@PathVariable("username") String username) {
        SysUser sysUser = userService.selectUserByUserName(username);
        if (StringUtils.isNull(sysUser)) {
            return R.fail("用户名或密码错误");
        }
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        return R.ok(sysUserVo);
    }

    @InnerAuth
    @GetMapping("/infoByPhone/{phone}")
    public R<LoginUser> infoByPhone(@PathVariable("phone") String phone) {
        SysUser sysUser = userService.selectUserByPhone(phone);
        if (StringUtils.isNull(sysUser)) {
            return R.fail("当前手机号未注册");
        }
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        return R.ok(sysUserVo);
    }
}
