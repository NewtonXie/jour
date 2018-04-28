package com.gzcb.creditcard.service;

import com.gzcb.creditcard.dao.entities.TFriend;
import com.gzcb.creditcard.dao.entities.TJour;
import com.gzcb.creditcard.dao.entities.TUser;

import java.util.List;

public interface FriendService {
    List<TFriend> selectAllJourByFriend(Integer userId);

}
