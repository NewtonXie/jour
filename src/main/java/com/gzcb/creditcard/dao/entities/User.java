package com.gzcb.creditcard.dao.entities;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {
    private static  final  long serialVersionUID = 1L;
    /**
     * 用户Id，主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String name;
    /**
     * 用户工号
     */
    private String number;

    /**
     * 密码
     * column t_user.user_password
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 手机号
     */
    private String mobile;

    /**
     * 工作状态：Y正常状态，N：离职，F：休假
     */
    private String status;

    /**
     * last_login_time
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
     * 是否锁定
     */
    private Short locked;

    /**
     * 错误次数
     */
    private Integer errorTimes;
//    /**
//     * 用户角色
//     */
//    private Userrole userrole;
//
//    public Userrole getUserrole() {
//        return userrole;
//    }
//
//    public void setUserrole(Userrole userrole) {
//        this.userrole = userrole;
//    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Short getLocked() {
        return locked;
    }

    public void setLocked(Short locked) {
        this.locked = locked;
    }

    public Integer getErrorTimes() {
        return errorTimes;
    }

    public void setErrorTimes(Integer errorTimes) {
        this.errorTimes = errorTimes;
    }
}