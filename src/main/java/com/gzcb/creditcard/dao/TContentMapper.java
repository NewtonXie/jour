package com.gzcb.creditcard.dao;

import com.github.pagehelper.Page;
import com.gzcb.creditcard.dao.entities.TContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface TContentMapper {
    /**
     * 消息推送
     * @param record
     * @return
     */
    int insertSelective(TContent record);
    /**
     * 查看好友
     */
    List<TContent> selectMessage(@Param(value = "type")Short type);
    /**
     *
     *将推送过的信息的类型改变成已经推送过type=3
     */
    int updateByPrimaryKeySelective(TContent record);

    /**
     *分页得到推送消息
     * @param createdAt
     * @return
     */
    Page<TContent> selectAllContentByPage(@Param(value = "createdAt")Date createdAt,@Param(value = "type")Short type);
    /**
     *
     * @mbggenerated 2018-04-17
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-17
     */
    int insert(TContent record);



    /**
     *
     * @mbggenerated 2018-04-17
     */
    TContent selectByPrimaryKey(Integer id);



    /**
     *
     * @mbggenerated 2018-04-17
     */
    int updateByPrimaryKey(TContent record);
}