package com.christianrondon.basiclogin.mapper;

import com.christianrondon.basiclogin.dto.user.request.CreateUser;
import com.christianrondon.basiclogin.entity.User;
import com.christianrondon.basiclogin.dto.user.response.UserResponse;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        if (user.getRole() != null) {
            response.setRoleId(user.getRole().getRoleId());
            response.setRoleName(user.getRole().getName().name());
        }

        return response;
    }

    public User toEntity(CreateUser request) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }
}
