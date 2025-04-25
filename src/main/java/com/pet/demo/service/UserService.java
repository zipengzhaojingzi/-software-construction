package com.pet.demo.service;

import com.pet.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    void save(User user);
    void delete(String id);
    void update(User user);
    List<User> findAll();
    User findOne(String id);
    User findByAccount(String Account);
    List<User> findByName(String userName);
    User login(String userAccount,String userPassword);
        void updateIdentity(String userId, String identity);
}
