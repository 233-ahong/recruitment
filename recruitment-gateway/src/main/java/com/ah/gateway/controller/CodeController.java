package com.ah.gateway.controller;

import com.ah.common.utils.web.AjaxResult;
import com.ah.gateway.domain.CodeBody;
import com.ah.gateway.service.ValidateCodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 26471
 * @date 2023/4/7 23:32
 * @description
 */
@RestController
public class CodeController {

    @Resource
    private ValidateCodeService validateCodeService;

    @PostMapping("/code")
    public AjaxResult getCode(@RequestBody CodeBody codeBody) {
        return validateCodeService.secondVerification(codeBody);
    }
}
