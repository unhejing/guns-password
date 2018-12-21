package com.stylefeng.guns.modular.billingDetail.vo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 账单明细表Vo
 * </p>
 *
 * @author unhejing
 * @since 2018-12-21
 */
public class BillingDetailVo extends Model<BillingDetailVo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 消费金额
     */
    private Integer expenseAmount;
    /**
     * 消费状态（0：支出，1：收入）
     */
    private Integer status;
    /**
     * 消费类型（关联类型表）
     */
    private Integer typeId;
    /**
     * 描述
     */
    private String description;
    /**
     * 截图
     */
    private String screenshot;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


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

    public Integer getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Integer expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BillingDetail{" +
        "id=" + id +
        ", userId=" + userId +
        ", expenseAmount=" + expenseAmount +
        ", status=" + status +
        ", typeId=" + typeId +
        ", description=" + description +
        ", screenshot=" + screenshot +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
