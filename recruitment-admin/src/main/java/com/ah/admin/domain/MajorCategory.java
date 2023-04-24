package com.ah.admin.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 专业类别表(MajorCategory)表实体类
 *
 * @author 26471
 * @since 2023-04-20 22:35:15
 */
public class MajorCategory extends Model<MajorCategory> implements Serializable {
    /***主键ID*/
    private Integer id;
    /***专业名称*/
    private String name;
    /***专业描述*/
    private String description;
    /***父级分类id*/
    private Integer parentId;
    /***创建时间*/
    private Date createdTime;
    /***更新时间*/
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
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
                .append("name", getName())
                .append("description", getDescription())
                .append("parentId", getParentId())
                .append("createdTime", getCreatedTime())
                .append("updatedTime", getUpdatedTime())
                .toString();
    }
}
