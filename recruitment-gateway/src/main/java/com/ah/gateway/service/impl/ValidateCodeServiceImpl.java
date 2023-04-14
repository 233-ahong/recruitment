package com.ah.gateway.service.impl;

import com.ah.common.constant.Constants;
import com.ah.common.exception.ServiceException;
import com.ah.common.redis.service.RedisService;
import com.ah.common.utils.Base64;
import com.ah.common.utils.StringUtils;
import com.ah.common.utils.uuid.IdUtils;
import com.ah.common.utils.web.AjaxResult;
import com.ah.gateway.config.properties.CaptchaProperties;
import com.ah.gateway.domain.CodeBody;
import com.ah.gateway.service.ValidateCodeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Producer;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码实现处理
 *
 * @author 26471
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    /**
     * 验证流水号
     */
    private static final String LOT_NUMBER = "lotNumber";
    /**
     * 验证输出信息
     */
    private static final String CAPTCHA_OUTPUT = "captchaOutput";
    /**
     * 验证通过标识
     */
    private static final String PASS_TOKEN = "passToken";
    /**
     * 验证通过时间戳
     */
    private static final String GEN_TIME = "genTime";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;
    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;
    @Resource
    private RedisService redisService;
    @Resource
    private CaptchaProperties captchaProperties;

    /**
     * 生成验证码
     */
    @Override
    public AjaxResult createCaptcha() throws IOException, ServiceException {
        AjaxResult ajax = AjaxResult.success();
        boolean captchaEnabled = captchaProperties.getEnabled();
        ajax.put("captchaEnabled", captchaEnabled);
        if (!captchaEnabled) {
            return ajax;
        }

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        String captchaType = captchaProperties.getType();
        // 生成验证码
        if (Constants.CODE_MATH.equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if (Constants.CODE_CHAR.equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisService.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }

    /**
     * 根据手机号生成6位数数字验证码
     *
     * @param phone 电话号码
     * @return AjaxResult
     * @throws ServiceException 验证码生成异常
     */
    @Override
    public AjaxResult createPhoneCode(String phone) throws IOException, ServiceException {
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid + phone;
        String code = IdUtils.mathRandom();
        redisService.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        return AjaxResult.success(uuid);
    }

    /**
     * 校验验证码
     */
    @Override
    public void checkCaptcha(String code, String uuid) throws ServiceException {
        if (StringUtils.isEmpty(code)) {
            throw new ServiceException("验证码不能为空");
        }
        if (StringUtils.isEmpty(uuid)) {
            throw new ServiceException("验证码已失效");
        }
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisService.getCacheObject(verifyKey);
        redisService.deleteObject(verifyKey);

        if (!code.equalsIgnoreCase(captcha)) {
            throw new ServiceException("验证码错误");
        }
    }

    @Override
    public void secondVerification(JSONObject json) throws ServiceException {
        //验证信息
        String genTime = json.getString(GEN_TIME);
        String lotNumber = json.getString(LOT_NUMBER);
        String captchaOutPut = json.getString(CAPTCHA_OUTPUT);
        String passToken = json.getString(PASS_TOKEN);//密钥信息
        String captchaKey = captchaProperties.getCaptchaKey();
        String domain = captchaProperties.getDomain();
        String captchaId = captchaProperties.getCaptchaId();
        String signToken = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, captchaKey).hmacHex(lotNumber);
        //请求信息
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("lot_number", lotNumber);
        queryParams.add("captcha_output", captchaOutPut);
        queryParams.add("pass_token", passToken);
        queryParams.add("gen_time", genTime);
        queryParams.add("sign_token", signToken);
        String url = String.format(domain + "/validate" + "?captcha_id=%s", captchaId);
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        try {
            JSONObject jsonObject = new JSONObject();
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(queryParams, headers);
            ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
            String resBody = response.getBody();
            jsonObject = JSON.parseObject(resBody);
            if (!"success".equals(jsonObject.getString("result"))) {
                logger.error(jsonObject.getString("reason"));
                throw new ServiceException("人机验证未通过! 请重新验证");
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public AjaxResult secondVerification(CodeBody codeBody) throws ServiceException {
        String lotNumber = codeBody.getLotNumber();
        String captchaKey = captchaProperties.getCaptchaKey();
        String domain = captchaProperties.getDomain();
        String captchaId = captchaProperties.getCaptchaId();
        String signToken = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, captchaKey).hmacHex(lotNumber);
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("lot_number", lotNumber);
        queryParams.add("captcha_output", codeBody.getCaptchaOutput());
        queryParams.add("pass_token", codeBody.getPassToken());
        queryParams.add("gen_time", codeBody.getGenTime());
        queryParams.add("sign_token", signToken);
        String url = String.format(domain + "/validate" + "?captcha_id=%s", captchaId);
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        try {
            JSONObject jsonObject = new JSONObject();
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(queryParams, headers);
            ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
            String resBody = response.getBody();
            jsonObject = JSON.parseObject(resBody);
            System.out.println(jsonObject);
            if (!"success".equals(jsonObject.getString("result"))) {
                logger.error(jsonObject.getString("reason"));
                throw new ServiceException("人机验证未通过! 请重新验证");
            }
            return createPhoneCode(codeBody.getPhone());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void checkPhoneCaptcha(String uuid, String code, String phone) {
        if (StringUtils.isEmpty(code)) {
            throw new ServiceException("验证码不能为空");
        }
        if (StringUtils.isEmpty(uuid)) {
            throw new ServiceException("验证码已失效");
        }
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid + phone;
        String captcha = redisService.getCacheObject(verifyKey);
        redisService.deleteObject(verifyKey);

        if (!code.equalsIgnoreCase(captcha)) {
            throw new ServiceException("验证码错误");
        }
    }
}
