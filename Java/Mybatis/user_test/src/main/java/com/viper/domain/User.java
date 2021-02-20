package com.viper.domain;

import lombok.Data;

/**
 * @author c1rew
 * @create 2021-02-20 23:30
 */
@Data
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
}
