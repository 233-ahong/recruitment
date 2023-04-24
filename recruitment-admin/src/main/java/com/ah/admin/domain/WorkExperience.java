package com.ah.admin.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作/实习经历表(WorkExperience)表实体类
 *
 * @author 26471
 * @since 2023-04-15 20:41:57
 */
public class WorkExperience extends Model<WorkExperience> implements Serializable {
    /***主键ID*/
    private Integer id;
    /***用户ID*/
    private Integer userId;
    /***公司名称*/
    private String companyName;
    /***职位名称*/
    private String positionName;
    /***开始时间*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    /***结束时间*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    /***经历类型*/
    private Integer experienceType;
    /***工作描述*/
    private String jobDescription;

    /***简历ID*/
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(Integer experienceType) {
        this.experienceType = experienceType;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
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
                .append("userId", getUserId())
                .append("companyName", getCompanyName())
                .append("positionName", getPositionName())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("experienceType", getExperienceType())
                .append("jobDescription", getJobDescription())
                .append("resumeId", getResumeId())
                .toString();
    }
}
