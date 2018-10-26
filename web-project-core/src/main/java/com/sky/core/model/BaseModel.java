package com.sky.core.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.IdType;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lxl on 2018/9/20.
 */
public class BaseModel<T extends Model> extends Model<T> implements Serializable{

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    public Integer id ;

    /**
     * 数据创建时间
     */
    @TableField(value = "create_time")
    public Date createTime ;

    /**
     * 数据创建人
     */
    @TableField(value = "create_user")
    public String createUser ;

    /**
     * 数据更新时间
     */
    @TableField(value = "update_time")
    public  Date updateTime ;

    /**
     * 数据更新人
     */
    @TableField(value = "update_user")
    public String updateUser ;

    /**
     * 乐观锁
     */
    @Version
    @TableField(value = "version")
    public Integer version ;

    /**
     * 假删除
     */
    @TableLogic
    @TableField(value = "isvalid")
    public Integer isvalid ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
