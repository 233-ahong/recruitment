package com.ah.api.factory;

import com.ah.api.domain.LoginUser;
import com.ah.api.domain.R;
import com.ah.api.remote.RemoteUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 26471
 * @date 2023/4/4 13:43
 * @description
 */
@Component
public class RemoteUserServiceImpl implements FallbackFactory<RemoteUserService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteUserServiceImpl.class);

    @Override
    public RemoteUserService create(Throwable cause) {
        return new RemoteUserService() {
            @Override
            public R<LoginUser> getUserInfoByName(String username, String source) {
                log.error("方法调用失败:{}", cause.getMessage());
                return R.fail("获取用户失败:" + cause.getMessage());
            }

            @Override
            public R<LoginUser> getUserInfoByPhone(String phone, String source) {
                log.error("方法调用失败:{}", cause.getMessage());
                return R.fail("获取用户失败:" + cause.getMessage());
            }
        };
    }
}
