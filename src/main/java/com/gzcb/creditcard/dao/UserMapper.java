package com.gzcb.creditcard.dao;

import com.gzcb.creditcard.dao.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    /**
     * 根据工号查询用户
     *
     * @param number
     * @return
     */
    User selectByUserNum(String number);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insertSelective(User user);

//    /**
//     * 分页显示所有用户
//     * @param roleId
//     * @param number
//     * @return
//     */
//    Page<User> listUserByPage(@Param("roleId") Integer roleId, @Param("number") String number);
//
//    /**
//     * 分页显示所有用户(普通用户)
//     * @param id
//     * @return
//     */
//    Page<User> listUserByPageTwo(Integer id);
//
//    /**
//     * 添加角色和用户关系
//     * @param userId
//     * @param roleId
//     * @return
//     */
//    int insertOrRole(Integer userId, Integer roleId);
//
//    /**
//     * 根据id进行删除
//     * @param id
//     * @return
//     */
//    int  deleteByPrimaryKey(Integer id);
//
//    /**
//     * 更新用户
//     * @param user
//     * @return
//     */
//    void updateByPrimaryKeySelective(User user);
//
//    /**
//     * 更新用户角色
//     * @param userId
//     * @param roleId
//     */
//    void updateToUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);


}