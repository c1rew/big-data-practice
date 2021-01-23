package com.viper.springboot.dao.impl;

import com.viper.springboot.dao.UserDAO;
import com.viper.springboot.domain.User;
import com.viper.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author c1rew
 * @create 2021-01-23 22:13
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有用户
     *
     * @return 用户信息
     */
    public List<User> listUsers() {
        return userMapper.listUsers();
    }

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    /**
     * 新增用户
     *
     * @param user 用户信息
     */
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    /**
     * 更新用户
     *
     * @param user 用户信息
     */
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    public void removeUser(Long id) {
        userMapper.removeUser(id);
    }
}
