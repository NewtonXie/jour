package com.gzcb.creditcard.service.impl;

import com.gzcb.creditcard.dao.TFriendMapper;
import com.gzcb.creditcard.dao.entities.TFriend;
import com.gzcb.creditcard.dao.entities.TJour;
import com.gzcb.creditcard.dao.entities.TUser;
import com.gzcb.creditcard.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FriendServiceImpl implements FriendService {
    @Autowired
    TFriendMapper tFriendMapper;
    /**
     * 根据用户id查找好友动态
     * @param userId
     * @return
     */
    @Override
    public List<TFriend> selectAllJourByFriend(Integer userId) {
        return tFriendMapper.selectAllJourByFriend(userId);
    }

}
