package com.ah.admin.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 教育背景表(EducationBackground)表实体类
 *
 * @author 26471
 * @since 2023-04-15 20:41:41
 */
public class EducationBackground extends Model<EducationBackground> implements Serializable {
    /***主键ID*/
    private Long id;
    /***用户ID*/
    private Long userId;
    /***学校*/
    private String school;
    /***专业类别*/
    private Integer majorCategory;
    /***专业名称*/
    private String majorName;
    /***学位*/
    private Integer degree;
    /***开始时间*/
    @JsonFormat(pattern = "yyyy-MM")
    private Date startTime;
    /***结束时间*/
    @JsonFormat(pattern = "yyyy-MM")
    private Date endTime;
    /***专业描述*/
    private String comment;
    /***学习方式*/
    private Integer learningMethod;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getMajorCategory() {
        return majorCategory;
    }

    public void setMajorCategory(Integer majorCategory) {
        this.majorCategory = majorCategory;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getLearningMethod() {
        return learningMethod;
    }

    public void setLearningMethod(Integer learningMethod) {
        this.learningMethod = learningMethod;
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
                .append("school", getSchool())
                .append("majorCategory", getMajorCategory())
                .append("majorName", getMajorName())
                .append("degree", getDegree())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("comment", getComment())
                .append("learningMethod", getLearningMethod())
                .append("resumeId", getResumeId())
                .toString();
    }
}
