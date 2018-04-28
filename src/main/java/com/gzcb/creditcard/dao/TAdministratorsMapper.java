package com.gzcb.creditcard.dao;

import com.gzcb.creditcard.dao.entities.TAdministrators;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TAdministratorsMapper {
    /**
     *
     * @mbggenerated 2018-04-17
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int insert(TAdministrators record);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int insertSelective(TAdministrators record);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    TAdministrators selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int updateByPrimaryKeySelective(TAdministrators record);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int updateByPrimaryKey(TAdministrators record);
}