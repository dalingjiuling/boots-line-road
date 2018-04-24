package com.line.road.modular.persistence.dao;

import com.line.road.modular.persistence.model.User;


public interface IUserDao {

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public User selectUserById(Long userId);

}
