package com.bravo.carrental.auth.api.mapper;

import com.bravo.carrental.auth.api.model.User;
import com.bravo.carrental.auth.api.model.UserDto;
import com.bravo.carrental.auth.api.model.UserRole;
import com.bravo.carrental.util.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements ModelMapper<UserDto, User> {
    @Override
    public User toEntity(UserDto userDto) {
        if (userDto == null){return null;}
        return User.builder().id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();}

    @Override
    public UserDto toDto(User user) {
        if (user == null){return null;}
        return UserDto.builder().id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();}
}
