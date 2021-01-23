package com.viper.springboot.service.impl;

import com.viper.springboot.dao.UserDAO;
import com.viper.springboot.domain.User;
import com.viper.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author c1rew
 * @create 2021-01-23 22:38
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * 查询所有用户
     *
     * @return 用户信息
     */
    public List<User> listUsers() {
        return userDAO.listUsers();
    }

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    /**
     * 新增用户
     *
     * @param user 用户信息
     */
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    /**
     * 更新用户
     *
     * @param user 用户信息
     */
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    public void removeUser(Long id) {
        userDAO.removeUser(id);
    }
}
