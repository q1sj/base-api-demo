package com.demo.sys.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Q1sj
 * @date 2023.3.29 16:53
 */
@Component
public class UserConverter implements Converter<String, User> {
    @Override
    public User convert(String source) {
        String[] split = source.split(",");
        if (split.length < 2) {
            throw new IllegalArgumentException(source);
        }
        User user = new User();
        user.setId(Integer.parseInt(split[0]));
        user.setName(split[1]);
        return user;
    }
}
