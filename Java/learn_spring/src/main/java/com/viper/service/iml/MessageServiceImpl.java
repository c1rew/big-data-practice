package com.viper.service.iml;

import com.viper.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author c1rew
 * @date 2021-01-13 15:53
 */

public class MessageServiceImpl implements MessageService {

    //@Autowired
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
