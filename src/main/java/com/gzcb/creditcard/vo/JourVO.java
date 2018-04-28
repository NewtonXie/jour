package com.gzcb.creditcard.vo;

import java.util.Date;

public class JourVO {
    private Integer pageNum;
    private Integer pageSize;
    private String mobile;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
     * 点赞数量
     */
    private Integer count;
    private Integer Type;

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
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

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}