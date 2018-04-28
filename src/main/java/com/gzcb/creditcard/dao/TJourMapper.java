package com.gzcb.creditcard.dao;

import com.github.pagehelper.Page;
import com.gzcb.creditcard.dao.entities.TJour;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TJourMapper {
    /**
     * 分页查询
     */
    Page<TJour> selectAllTJourByPage(@Param(value = "mobile")String mobile ,@Param(value = "type")Integer type);

    /**
     * 获取所有的jour
     * @param userId
     * @return
     */
    List<TJour> selectAllJour(@Param(value = "userId")Integer userId,@Param(value = "type")Integer type);
    /**
     *
     * @mbggenerated 2018-04-17
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int insert(TJour record);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int insertSelective(TJour record);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    TJour selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int updateByPrimaryKeySelective(TJour record);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int updateByPrimaryKey(TJour record);
}