package com.viper.springboot.web;

import com.viper.springboot.domain.User;
import com.viper.springboot.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author c1rew
 * @create 2021-01-23 22:41
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     *
     * @return 用户信息
     */
    @GetMapping("/")
    public List<User> listUsers() {
        return userService.listUsers();
    }

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    /**
     * 新增用户
     *
     * @param user 用户信息
     */
    @PostMapping("/")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    /**
     * 更新用户
     *
     * @param user 用户信息
     */
    @PutMapping("/{id}")
    public void updateUser(@RequestBody User user) {
        System.out.println(user);
        userService.updateUser(user);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
    }
}
