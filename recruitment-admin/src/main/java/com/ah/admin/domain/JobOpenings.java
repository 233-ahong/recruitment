package com.ah.admin.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 招聘职位表(JobOpenings)表实体类
 *
 * @author 26471
 * @since 2023-04-15 20:41:43
 */
public class JobOpenings extends Model<JobOpenings> implements Serializable {
    /***ID*/
    private Integer id;
    /***招聘简章ID*/
    private Integer recruitmentInfoId;
    /***职位名称*/
    private String jobTitle;
    /***工作地点*/
    private String workLocation;
    /***招聘人数*/
    private Integer recruitNum;
    /***薪资范围*/
    private String salaryRange;
    /***岗位介绍*/
    private String jobDescription;
    /***工作职责*/
    private String jobResponsibilities;
    /***岗位要求*/
    private String jobRequirements;
    /***招聘状态*/
    private String recruitmentStatus;
    /***发布时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publicationDate;
    /***截止时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    /***创建者ID*/
    private Integer creatorId;

    /**
     * 简历ID
     */
    private Integer resumeId;

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecruitmentInfoId() {
        return recruitmentInfoId;
    }

    public void setRecruitmentInfoId(Integer recruitmentInfoId) {
        this.recruitmentInfoId = recruitmentInfoId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public Integer getRecruitNum() {
        return recruitNum;
    }

    public void setRecruitNum(Integer recruitNum) {
        this.recruitNum = recruitNum;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobResponsibilities() {
        return jobResponsibilities;
    }

    public void setJobResponsibilities(String jobResponsibilities) {
        this.jobResponsibilities = jobResponsibilities;
    }

    public String getJobRequirements() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }

    public String getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public void setRecruitmentStatus(String recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
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
                .append("recruitmentInfoId", getRecruitmentInfoId())
                .append("jobTitle", getJobTitle())
                .append("workLocation", getWorkLocation())
                .append("recruitNum", getRecruitNum())
                .append("salaryRange", getSalaryRange())
                .append("jobDescription", getJobDescription())
                .append("jobResponsibilities", getJobResponsibilities())
                .append("jobRequirements", getJobRequirements())
                .append("recruitmentStatus", getRecruitmentStatus())
                .append("publicationDate", getPublicationDate())
                .append("endDate", getEndDate())
                .append("creatorId", getCreatorId())
                .append("resumeId", getResumeId())
                .toString();
    }
}
