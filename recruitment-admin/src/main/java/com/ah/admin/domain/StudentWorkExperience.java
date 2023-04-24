package com.ah.admin.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生工作经历表(StudentWorkExperience)表实体类
 *
 * @author 26471
 * @since 2023-04-15 20:37:13
 */
public class StudentWorkExperience extends Model<StudentWorkExperience> implements Serializable {
    /***用户ID*/
    private Integer id;
    private Integer userId;
    /***学校名称*/
    private String schoolName;
    /***组织名称*/
    private String organizationName;
    /***职位名称*/
    private String position;
    /***开始时间*/
    private Date startTime;
    /***结束时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /***经历描述*/
    private String description;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                .append("schoolName", getSchoolName())
                .append("organizationName", getOrganizationName())
                .append("position", getPosition())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("description", getDescription())
                .append("resumeId", getResumeId())
                .toString();
    }
}
