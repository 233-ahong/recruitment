package com.ah.api.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author 26471
 * @date 2023/4/9 22:27
 * @description
 */
public class FileInfo {
    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;
    /**
     * 访问id
     */
    private String accessId;

    /**
     * 编码
     */
    private String encodedPolicy;

    /**
     * 签名
     */
    private String signature;

    /**
     * 文件前缀
     */
    private String dir;

    /**
     * 上传地址
     */
    private String host;

    /**
     * 过期时间
     */
    private long expireTime;

    public FileInfo() {
    }

    public FileInfo(String accessId, String encodedPolicy, String signature, String dir, String host, long expireTime) {
        this.accessId = accessId;
        this.encodedPolicy = encodedPolicy;
        this.signature = signature;
        this.dir = dir;
        this.host = host;
        this.expireTime = expireTime;
    }


    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getEncodedPolicy() {
        return encodedPolicy;
    }

    public void setEncodedPolicy(String encodedPolicy) {
        this.encodedPolicy = encodedPolicy;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("accessId", getAccessId())
                .append("encodedPolicy", getEncodedPolicy())
                .append("signature", getSignature())
                .append("hose", getHost())
                .append("dir", getDir())
                .append("expireTime", getExpireTime())
                .toString();
    }
}
