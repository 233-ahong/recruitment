package com.ah.admin.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 招聘简章表(RecruitmentInfo)表实体类
 *
 * @author 26471
 * @since 2023-04-15 20:41:47
 */
public class RecruitmentInfo extends Model<RecruitmentInfo> implements Serializable {
    /***招聘信息ID*/
    private Integer id;
    /***公司ID*/
    private Integer companyId;
    /***招聘对象*/
    private String recruitTarget;
    /***薪资待遇*/
    private Double salary;
    /***招聘人数*/
    private Integer recruitNum;
    /***工作地点*/
    private String workLocation;
    /***工作职责*/
    private String jobDescription;
    /***任职要求*/
    private String jobRequirements;
    /***招聘标题*/
    private String recruitTitle;
    /***发布日期*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;
    /***截止日期*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireDate;
    /***状态（0：未发布，1：已发布）*/
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getRecruitTarget() {
        return recruitTarget;
    }

    public void setRecruitTarget(String recruitTarget) {
        this.recruitTarget = recruitTarget;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getRecruitNum() {
        return recruitNum;
    }

    public void setRecruitNum(Integer recruitNum) {
        this.recruitNum = recruitNum;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobRequirements() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }

    public String getRecruitTitle() {
        return recruitTitle;
    }

    public void setRecruitTitle(String recruitTitle) {
        this.recruitTitle = recruitTitle;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("companyId", getCompanyId())
                .append("recruitTarget", getRecruitTarget())
                .append("salary", getSalary())
                .append("recruitNum", getRecruitNum())
                .append("workLocation", getWorkLocation())
                .append("jobDescription", getJobDescription())
                .append("jobRequirements", getJobRequirements())
                .append("recruitTitle", getRecruitTitle())
                .append("publishDate", getPublishDate())
                .append("expireDate", getExpireDate())
                .append("status", getStatus())
                .toString();
    }
}
