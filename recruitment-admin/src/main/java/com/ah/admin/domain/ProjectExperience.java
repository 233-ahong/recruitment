package com.ah.admin.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 比赛/项目经历表(ProjectExperience)表实体类
 *
 * @author 26471
 * @since 2023-04-15 20:41:46
 */
public class ProjectExperience extends Model<ProjectExperience> implements Serializable {
    /***主键*/
    private Integer id;
    /***用户ID*/
    private Integer userId;
    /***项目/比赛名称*/
    private String projectName;
    /***团队规模*/
    private Integer teamSize;
    /***担任职务*/
    private String holdPosition;
    /***开始时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /***结束时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /***描述*/
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    public String getHoldPosition() {
        return holdPosition;
    }

    public void setHoldPosition(String holdPosition) {
        this.holdPosition = holdPosition;
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
                .append("projectName", getProjectName())
                .append("teamSize", getTeamSize())
                .append("holdPosition", getHoldPosition())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("description", getDescription())
                .append("resumeId", getResumeId())
                .toString();
    }
}
