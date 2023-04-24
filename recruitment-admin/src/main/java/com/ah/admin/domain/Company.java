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
 * 公司表(Company)表实体类
 *
 * @author 26471
 * @since 2023-04-15 20:41:39
 */
public class Company extends Model<Company> implements Serializable {
    /***公司ID*/
    private Integer id;
    /***公司名称*/
    private String name;
    /***公司描述*/
    private String description;
    /***公司地址*/
    private String address;
    /***联系电话*/
    private String phone;
    /***联系邮箱*/
    private String email;
    /***公司LOGO的URL*/
    private String logoUrl;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Date getcreatedTime() {
        return createdTime;
    }

    public void setcreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getupdatedTime() {
        return updatedTime;
    }

    public void setupdatedTime(Date updatedTime) {
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
                .append("name", getName())
                .append("description", getDescription())
                .append("address", getAddress())
                .append("phone", getPhone())
                .append("email", getEmail())
                .append("logoUrl", getLogoUrl())
                .append("createdTime", getcreatedTime())
                .append("updatedTime", getupdatedTime())
                .toString();
    }
}
