package com.ah.admin.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 投递记录表(DeliveryRecord)表实体类
 *
 * @author 26471
 * @since 2023-04-15 20:41:40
 */
public class DeliveryRecord extends Model<DeliveryRecord> implements Serializable {
    /***ID*/
    private Integer id;
    /***用户ID*/
    private Integer userId;
    /***职位ID*/
    private Integer positionId;
    /***简历ID*/
    private Integer resumeId;
    /***投递时间*/
    @TableField(fill = FieldFill.INSERT)
    private Date deliveryTime;
    /***状态（0：未处理，1：已处理，2：已通知）*/
    private Integer status;
    /***面试时间*/
    private Date interviewTime;
    /***面试地点*/
    private String interviewAddress;
    /***面试官*/
    private String interviewer;
    /***面试结果（0：未知，1：通过，2：淘汰）*/
    private Integer interviewResult;
    /***反馈*/
    private String feedback;
    /***创建时间*/
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    /***更新时间*/
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

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

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(Date interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getInterviewAddress() {
        return interviewAddress;
    }

    public void setInterviewAddress(String interviewAddress) {
        this.interviewAddress = interviewAddress;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public Integer getInterviewResult() {
        return interviewResult;
    }

    public void setInterviewResult(Integer interviewResult) {
        this.interviewResult = interviewResult;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getcreatedTime() {
        return createdTime;
    }

    public void setcreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
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
                .append("positionId", getPositionId())
                .append("resumeId", getResumeId())
                .append("deliveryTime", getDeliveryTime())
                .append("status", getStatus())
                .append("interviewTime", getInterviewTime())
                .append("interviewAddress", getInterviewAddress())
                .append("interviewer", getInterviewer())
                .append("interviewResult", getInterviewResult())
                .append("feedback", getFeedback())
                .append("createdTime", getcreatedTime())
                .append("updatedTime", getUpdatedTime())
                .toString();
    }
}
