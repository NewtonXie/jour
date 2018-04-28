package com.gzcb.creditcard.dao;

import com.github.pagehelper.Page;
import com.gzcb.creditcard.dao.entities.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TUserMapper {
    /**
     * 登陆
     */
    TUser login(@Param(value = "mobile") String mobile,@Param(value = "passWord") String passWord);
    TUser selectByMobile(@Param(value = "mobile") String mobile);
    /**
     * 查看好友
     */
    List<TUser> selectAllFriend(@Param(value = "userId")Integer userId);

    /**
     * 查看没有被锁定的用户
     * @return
     */
    List<TUser> selectAllUser();
    /**
     * 分页查询
     */
    Page<TUser> selectAllUserByPage(@Param(value = "name")String name,
                                     @Param(value = "mobile")String mobile);
    /**
     *
     * @mbggenerated 2018-04-17
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int insert(TUser record);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int insertSelective(TUser record);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    TUser selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int updateByPrimaryKeySelective(TUser record);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int updateByPrimaryKey(TUser record);
}