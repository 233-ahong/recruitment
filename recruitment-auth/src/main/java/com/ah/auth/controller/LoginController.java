package com.ah.auth.controller;

import com.ah.api.domain.LoginUser;
import com.ah.api.domain.R;
import com.ah.auth.domain.LoginBody;
import com.ah.auth.service.LoginService;
import com.ah.common.security.auth.AuthUtil;
import com.ah.common.security.service.TokenService;
import com.ah.common.utils.JwtUtils;
import com.ah.common.utils.SecurityUtils;
import com.ah.common.utils.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 26471
 * @date 2023/4/3 21:40
 * @description
 */
@RestController
public class LoginController {

    @Resource
    TokenService tokenService;

    @Resource
    LoginService loginService;

    @PostMapping("/loginByPassword")
    public R<?> login(@RequestBody LoginBody loginBody) {
        LoginUser login = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        return R.ok(tokenService.createToken(login));
    }

    @PostMapping("/loginByPhone")
    public R<?> loginByPhone(@RequestBody LoginBody loginBody) {
        LoginUser login = loginService.loginByPhone(loginBody.getPhone());
        return R.ok(tokenService.createToken(login));
    }

    @DeleteMapping("/logOut")
    public R<?> logOut(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        System.out.println(token);
        if (StringUtils.isNotEmpty(token)) {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
        }
        return R.ok();
    }

}
