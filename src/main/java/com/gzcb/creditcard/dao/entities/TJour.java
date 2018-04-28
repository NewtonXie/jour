package com.gzcb.creditcard.dao.entities;

import java.util.Date;

public class TJour {
    /**
     * 日记Id，主键
     */
    private Integer id;

    /**
     * 上传所在地
     */
    private String sSite;

    /**
     * 上传内容
     */
    private String jour;

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
     * 点赞数量
     */
    private Integer count;

    /**
     * 0-个人日记1-好友可见动态
     */
    private Short type;
    /**
     * cron表达式
     */
    private String cron;
    /**
     * 标题
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    /**
     * 日记Id，主键
     * @return id 日记Id，主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 日记Id，主键
     * @param id 日记Id，主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 上传所在地
     * @return s_site 上传所在地
     */
    public String getsSite() {
        return sSite;
    }

    /**
     * 上传所在地
     * @param sSite 上传所在地
     */
    public void setsSite(String sSite) {
        this.sSite = sSite == null ? null : sSite.trim();
    }

    /**
     * 上传内容
     * @return jour 上传内容
     */
    public String getJour() {
        return jour;
    }

    /**
     * 上传内容
     * @param jour 上传内容
     */
    public void setJour(String jour) {
        this.jour = jour == null ? null : jour.trim();
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
     * 点赞数量
     * @return count 点赞数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 点赞数量
     * @param count 点赞数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 0-个人日记1-好友可见动态
     * @return type 0-个人日记1-好友可见动态
     */
    public Short getType() {
        return type;
    }

    /**
     * 0-个人日记1-好友可见动态
     * @param type 0-个人日记1-好友可见动态
     */
    public void setType(Short type) {
        this.type = type;
    }
}