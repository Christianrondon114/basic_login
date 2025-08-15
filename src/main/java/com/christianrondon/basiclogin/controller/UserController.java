package com.christianrondon.basiclogin.controller;

import com.christianrondon.basiclogin.dto.user.request.CreateUser;
import com.christianrondon.basiclogin.dto.user.request.UpdateRole;
import com.christianrondon.basiclogin.dto.user.response.UserResponse;
import com.christianrondon.basiclogin.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> showAllUsers() {
        return userService.showAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUser request) {
        return userService.createUser(request);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);

    }

    @PatchMapping("/role")
    public UserResponse updateUserRole(@RequestBody @Valid UpdateRole request) {
        return userService.updateUserRole(request);
    }



    }

