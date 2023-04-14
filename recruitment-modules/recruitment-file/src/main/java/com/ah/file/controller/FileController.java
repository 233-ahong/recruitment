package com.ah.file.controller;

import com.ah.api.domain.FileInfo;
import com.ah.api.domain.R;
import com.ah.file.utils.AliyunUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 26471
 * @date 2023/4/9 21:51
 * @description
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    OSS ossClient;

    @Resource
    AliyunUtils aliyunUtils;

    @GetMapping("/oss/{filename}")
    @CrossOrigin
    public R<FileInfo> policy(@PathVariable String filename) {
        String host = "https://" + aliyunUtils.getBucket() + "." + aliyunUtils.getEndpoint();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // 用户上传文件时指定的前缀,我们希望以日期作为一个目录
        String dir = format + "/";

        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 5242880);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            FileInfo info = new FileInfo(aliyunUtils.getAccessKey(), encodedPolicy, postSignature, dir, host, expireTime);
            return R.ok(info);
        } catch (Exception e) {
            throw new ServiceException("上传信息获取失败");
        } finally {
            ossClient.shutdown();
        }
    }
}
