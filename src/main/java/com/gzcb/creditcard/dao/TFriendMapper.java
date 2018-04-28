package com.gzcb.creditcard.dao;

import com.gzcb.creditcard.dao.entities.TFriend;
import com.gzcb.creditcard.dao.entities.TJour;
import com.gzcb.creditcard.dao.entities.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TFriendMapper {
    /**
     * 查看好友动态
     * @param userId
     * @return
     */
    List<TFriend> selectAllJourByFriend(@Param(value = "userId")Integer userId);
    /**
     * 查看好友
     */
    List<TUser> selectAllFriend(@Param(value = "userId")Integer userId);
    /**
     * 添加好友
     * @param record
     * @return
     */
    int insertSelective(TFriend record);
    /**
     *
     * @mbggenerated 2018-04-17
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int insert(TFriend record);



    /**
     *
     * @mbggenerated 2018-04-17
     */
    TFriend selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int updateByPrimaryKeySelective(TFriend record);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int updateByPrimaryKey(TFriend record);
}