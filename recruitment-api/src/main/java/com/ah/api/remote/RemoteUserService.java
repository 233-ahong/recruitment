package com.ah.api.remote;

import com.ah.api.domain.LoginUser;
import com.ah.api.domain.R;
import com.ah.api.factory.RemoteUserServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author 26471
 * @date 2023/4/4 13:41
 * @description
 */
@FeignClient(contextId = "userService", value = "recruitment-admin", fallbackFactory = RemoteUserServiceImpl.class)
public interface RemoteUserService {

    @GetMapping("/sys/use/infoByName/{username}")
    public R<LoginUser> getUserInfoByName(@PathVariable("username") String username, @RequestHeader("from-cource") String source);

    @GetMapping("/sys/use/infoByPhone/{phone}")
    public R<LoginUser> getUserInfoByPhone(@PathVariable("phone") String phone, @RequestHeader("from-cource") String source);

}
