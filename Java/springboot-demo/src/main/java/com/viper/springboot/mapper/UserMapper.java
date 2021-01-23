package com.viper.springboot.mapper;

import com.viper.springboot.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户管理 Mapper 组件
 *
 * @author c1rew
 * @create 2021-01-23 22:00
 */
@Mapper
public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return 用户信息
     */
    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    List<User> listUsers();

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Select("SELECT * FROM user WHERE id=#{id}")
    User getUserById(@Param("id") Long id);

    /**
     * 新增用户
     *
     * @param user 用户信息
     */
    @Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
    void saveUser(User user);

    /**
     * 更新用户
     *
     * @param user 用户信息
     */
    @Update("UPDATE user SET name=#{name}, age=#{age} WHERE id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    @Delete("DELETE FROM user where id=#{id}")
    void removeUser(@Param("id") Long id);
}
