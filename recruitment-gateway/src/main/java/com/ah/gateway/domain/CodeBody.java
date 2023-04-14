package com.ah.gateway.domain;

/**
 * @author 26471
 * @date 2023/4/7 23:56
 * @description
 */
public class CodeBody {
    private String captchaOutput;

    private String genTime;

    private String lotNumber;

    private String passToken;

    private String phone;

    public String getCaptchaOutput() {
        return captchaOutput;
    }

    public void setCaptchaOutput(String captchaOutput) {
        this.captchaOutput = captchaOutput;
    }

    public String getGenTime() {
        return genTime;
    }

    public void setGenTime(String genTime) {
        this.genTime = genTime;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getPassToken() {
        return passToken;
    }

    public void setPassToken(String passToken) {
        this.passToken = passToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "CodeBody{" +
                "captchaOutput='" + captchaOutput + '\'' +
                ", genTime='" + genTime + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", passToken='" + passToken + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
