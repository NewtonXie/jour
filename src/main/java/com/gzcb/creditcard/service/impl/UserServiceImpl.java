package com.gzcb.creditcard.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzcb.creditcard.dao.TUserMapper;
import com.gzcb.creditcard.dao.entities.TUser;
import com.gzcb.creditcard.dao.entities.User;
import com.gzcb.creditcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserMapper tUserMapper;
    @Override
    public TUser selectUser(String name,String passWord) {
        return tUserMapper.login(name,passWord);
    }
    @Override
    public int addUser(TUser tUser) {
        return tUserMapper.insertSelective(tUser);
    }
    /**
     * 根据用户id查找好友
     * @param userId
     * @return
     */
    @Override
    public List<TUser> selectAllFriend(Integer userId) {
        return tUserMapper.selectAllFriend(userId);
    }

    @Override
    public List<TUser> selectAllUser() {
        return tUserMapper.selectAllUser();
    }

    @Override
    public TUser selectUserById(Integer id) {
        return tUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<TUser> selectAllUserByPage(Page<TUser> page,String name, String mobile) {
        PageHelper.startPage(page.getPageNum() <= 0 ? 1 : page.getPageNum(), page.getPageSize() <= 0 ? 15 : page.getPageSize());
        return tUserMapper.selectAllUserByPage(name,mobile);
    }

    @Override
    public int updateUser(TUser tUser) {
        return tUserMapper.updateByPrimaryKeySelective(tUser);
    }

    @Override
    public TUser selectUser(String mobile) {
        return tUserMapper.selectByMobile(mobile);
    }

}
