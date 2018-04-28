package com.gzcb.creditcard.service;

import com.github.pagehelper.Page;
import com.gzcb.creditcard.dao.entities.TUser;
import com.gzcb.creditcard.dao.entities.User;
import io.swagger.models.auth.In;

import java.util.List;

public interface UserService {
   int addUser(TUser tUser);

   TUser selectUser(String name,String passWord);
   List<TUser> selectAllFriend(Integer userId);
   List<TUser> selectAllUser();
   TUser selectUserById(Integer id);
   Page<TUser> selectAllUserByPage(Page<TUser> page,String name,String mobile);
   int updateUser(TUser tUser);
   TUser selectUser(String mobile);
}
