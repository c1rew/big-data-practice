package com.viper.springboot.dao;

import com.viper.springboot.domain.User;

import java.util.List;

/**
 * @author c1rew
 * @create 2021-01-23 21:57
 */
public interface UserDAO {
    /**
     * 查询所有用户
     *
     * @return 用户信息
     */
    List<User> listUsers();

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    User getUserById(Long id);

    /**
     * 新增用户
     *
     * @param user 用户信息
     */
    void saveUser(User user);

    /**
     * 更新用户
     *
     * @param user 用户信息
     */
    void updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void removeUser(Long id);
}
