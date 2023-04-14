package com.ah.gateway.handler;

import com.ah.common.exception.ServiceException;
import com.ah.common.utils.web.AjaxResult;
import com.ah.gateway.service.ValidateCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 验证码获取
 *
 * @author 26471
 */
@Component
public class ValidateCodeHandler implements HandlerFunction<ServerResponse> {
    @Resource
    private ValidateCodeService validateCodeService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        String[] split = serverRequest.toString().split("/");
        AjaxResult ajax;
        try {
            ajax = validateCodeService.createPhoneCode(split[2]);
        } catch (ServiceException | IOException e) {
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(ajax));
    }
}
