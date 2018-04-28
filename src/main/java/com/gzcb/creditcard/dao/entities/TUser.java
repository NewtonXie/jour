package com.gzcb.creditcard.dao.entities;

import java.util.Date;

public class TUser {
    /**
     * 用户Id，主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String toux;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 1-男0-女
     */
    private Short sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;

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
     * 是否锁定0：否，1：是
     */
    private Short locked;

    /**
     * 
     */
    private Integer errorTimes;

    /**
     * 用户Id，主键
     * @return id 用户Id，主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 用户Id，主键
     * @param id 用户Id，主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户名
     * @return name 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 用户名
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 手机号
     * @return mobile 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 头像
     * @return toux 头像
     */
    public String getToux() {
        return toux;
    }

    /**
     * 头像
     * @param toux 头像
     */
    public void setToux(String toux) {
        this.toux = toux == null ? null : toux.trim();
    }

    /**
     * 年龄
     * @return age 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 年龄
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 性别 1-男0-女
     * @return sex 性别 1-男0-女
     */
    public Short getSex() {
        return sex;
    }

    /**
     * 性别 1-男0-女
     * @param sex 性别 1-男0-女
     */
    public void setSex(Short sex) {
        this.sex = sex;
    }

    /**
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 最后一次登录时间
     * @return last_login_time 最后一次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 最后一次登录时间
     * @param lastLoginTime 最后一次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
     * 是否锁定0：否，1：是
     * @return locked 是否锁定0：否，1：是
     */
    public Short getLocked() {
        return locked;
    }

    /**
     * 是否锁定0：否，1：是
     * @param locked 是否锁定0：否，1：是
     */
    public void setLocked(Short locked) {
        this.locked = locked;
    }

    /**
     * 
     * @return error_times 
     */
    public Integer getErrorTimes() {
        return errorTimes;
    }

    /**
     * 
     * @param errorTimes 
     */
    public void setErrorTimes(Integer errorTimes) {
        this.errorTimes = errorTimes;
    }
}