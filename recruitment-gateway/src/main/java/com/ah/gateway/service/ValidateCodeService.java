package com.ah.gateway.service;


import com.ah.common.exception.ServiceException;
import com.ah.common.utils.web.AjaxResult;
import com.ah.gateway.domain.CodeBody;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * 验证码处理
 *
 * @author 26471
 */
public interface ValidateCodeService {
    /**
     * 生成验证码
     */
    AjaxResult createCaptcha() throws IOException, ServiceException;

    AjaxResult createPhoneCode(String phone) throws IOException, ServiceException;

    /**
     * 校验验证码
     */
    public void checkCaptcha(String key, String value) throws ServiceException;

    public void secondVerification(JSONObject json) throws ServiceException;

    public AjaxResult secondVerification(CodeBody codeBody) throws ServiceException;

    void checkPhoneCaptcha(String uuid, String code, String phone);
}
