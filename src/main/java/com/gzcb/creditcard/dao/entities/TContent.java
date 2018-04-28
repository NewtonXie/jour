package com.gzcb.creditcard.dao.entities;

import java.util.Date;

public class TContent {
    /**
     * 推送信息Id，主键
     */
    private Integer id;

    /**
     * 上传内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 创建人
     */
    private Integer createdBy;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 更新人
     */
    private Integer updatedBy;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * 备注
     */
    private String remark;

    /**
     * 推送信息类型（0-向所有人推送 1-推送给单个用户 2-为不推送）
     */
    private Short type;

    /**
     * 推送的手机号
     */
    private Integer pushUserId;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 推送信息Id，主键
     * @return id 推送信息Id，主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 推送信息Id，主键
     * @param id 推送信息Id，主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 上传内容
     * @return content 上传内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 上传内容
     * @param content 上传内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 创建时间
     * @return created_at 创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 创建时间
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 创建人
     * @return created_by 创建人
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 创建人
     * @param createdBy 创建人
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 更新时间
     * @return updated_at 更新时间
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 更新时间
     * @param updatedAt 更新时间
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 更新人
     * @return updated_by 更新人
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 更新人
     * @param updatedBy 更新人
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * cron表达式
     * @return cron cron表达式
     */
    public String getCron() {
        return cron;
    }

    /**
     * cron表达式
     * @param cron cron表达式
     */
    public void setCron(String cron) {
        this.cron = cron == null ? null : cron.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 推送信息类型（0-向所有人推送 1-推送给单个用户）
     * @return type 推送信息类型（0-向所有人推送 1-推送给单个用户）
     */
    public Short getType() {
        return type;
    }

    /**
     * 推送信息类型（0-向所有人推送 1-推送给单个用户）
     * @param type 推送信息类型（0-向所有人推送 1-推送给单个用户）
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * 推送的手机号
     * @return push_user_id 推送的手机号
     */
    public Integer getPushUserId() {
        return pushUserId;
    }

    /**
     * 推送的手机号
     * @param pushUserId 推送的手机号
     */
    public void setPushUserId(Integer pushUserId) {
        this.pushUserId = pushUserId;
    }
}