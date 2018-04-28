package com.gzcb.creditcard.dao.entities;

import java.util.Date;

public class TFriend {
    /**
     * Id，主键
     */
    private Integer id;

    /**
     * 好友备注
     */
    private String name;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 好友id
     */
    private Integer friendId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 创建人
     */
    private Integer createdBy;

    /**
     * 好友类型0：普通好友，1：关注好友
     */
    private Short type;

    /**
     * 是否锁定好友0：否，1：是
     */
    private Short locked;

    /**
     * 动态可见0-不可见动态1-可见动态
     */
    private Short status;

    private TJour tJour;

    private TUser tUser;

    public TUser gettUser() {
        return tUser;
    }

    public void settUser(TUser tUser) {
        this.tUser = tUser;
    }

    public TJour gettJour() {
        return tJour;
    }

    public void settJour(TJour tJour) {
        this.tJour = tJour;
    }

    /**
     * Id，主键
     * @return id Id，主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * Id，主键
     * @param id Id，主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 好友备注
     * @return name 好友备注
     */
    public String getName() {
        return name;
    }

    /**
     * 好友备注
     * @param name 好友备注
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 用户id
     * @return user_id 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户id
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 好友id
     * @return friend_id 好友id
     */
    public Integer getFriendId() {
        return friendId;
    }

    /**
     * 好友id
     * @param friendId 好友id
     */
    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
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
     * 好友类型0：普通好友，1：关注好友
     * @return type 好友类型0：普通好友，1：关注好友
     */
    public Short getType() {
        return type;
    }

    /**
     * 好友类型0：普通好友，1：关注好友
     * @param type 好友类型0：普通好友，1：关注好友
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * 是否锁定好友0：否，1：是
     * @return locked 是否锁定好友0：否，1：是
     */
    public Short getLocked() {
        return locked;
    }

    /**
     * 是否锁定好友0：否，1：是
     * @param locked 是否锁定好友0：否，1：是
     */
    public void setLocked(Short locked) {
        this.locked = locked;
    }

    /**
     * 动态可见0-不可见动态1-可见动态
     * @return status 动态可见0-不可见动态1-可见动态
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 动态可见0-不可见动态1-可见动态
     * @param status 动态可见0-不可见动态1-可见动态
     */
    public void setStatus(Short status) {
        this.status = status;
    }
}