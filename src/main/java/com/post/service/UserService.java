package com.post.service;

import com.post.dto.UserDto;
import com.post.entity.User;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);

    User findUserByEmail(String email);

    User getUser(Long id);

    List<UserDto> findAllUsers();

    UserDto deleteUser(Long id);
}
