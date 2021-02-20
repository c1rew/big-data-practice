package com.viper.mapper;

import com.viper.domain.User;

/**
 * @author c1rew
 * @create 2021-02-20 23:30
 */
public interface UserMapper {

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User findById(Long id);
}
