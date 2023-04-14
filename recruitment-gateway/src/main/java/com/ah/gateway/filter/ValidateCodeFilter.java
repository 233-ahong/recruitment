package com.ah.gateway.filter;

import com.ah.common.utils.ServletUtils;
import com.ah.gateway.config.properties.CaptchaProperties;
import com.ah.gateway.service.ValidateCodeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 验证码过滤器
 *
 * @author 26471
 */
@Component
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object> {
    private final static Set<String> SET_URL = new HashSet<>(Arrays.asList("/auth/loginByPassword", "/auth/register", "/auth/loginByPhone"));
    private static final String CODE = "code";
    private static final String UUID = "uuid";
    private static final String PHONE = "phone";
    @Resource
    private ValidateCodeService validateCodeService;
    @Resource
    private CaptchaProperties captchaProperties;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String requestPath = request.getURI().getPath();
            // 非登录/注册请求或验证码关闭，不处理
            if (!SET_URL.contains(requestPath) || !captchaProperties.getEnabled()) {
                return chain.filter(exchange);
            }

            try {
                String rspStr = resolveBodyFromRequest(request);
                JSONObject obj = JSON.parseObject(rspStr);
                if ("/auth/loginByPassword".equals(requestPath)) {
                    validateCodeService.secondVerification(obj);
                } else if ("/auth/loginByPhone".equals(requestPath)) {
                    validateCodeService.checkPhoneCaptcha(obj.getString(UUID), obj.getString(CODE), obj.getString(PHONE));
                }
            } catch (Exception e) {
                return ServletUtils.webFluxResponseWriter(exchange.getResponse(), e.getMessage());
            }
            return chain.filter(exchange);
        };
    }

    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        // 获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }
}
