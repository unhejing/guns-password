package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 消费类型表
 * </p>
 *
 * @author unhejing
 * @since 2018-12-21
 */
@TableName("biz_consumption_type")
public class ConsumptionType extends Model<ConsumptionType> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 类型名称
     */
    private String name;
    /**
     * 关联用户
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 支付状态(0:支出，1：收入)
     */
    private Integer status;


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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ConsumptionType{" +
        "id=" + id +
        ", name=" + name +
        ", userId=" + userId +
        ", sort=" + sort +
        ", status=" + status +
        "}";
    }
}
