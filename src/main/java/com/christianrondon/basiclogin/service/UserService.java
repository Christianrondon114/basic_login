package com.christianrondon.basiclogin.service;

import com.christianrondon.basiclogin.config.SecurityConfig;
import com.christianrondon.basiclogin.dto.user.request.CreateUser;
import com.christianrondon.basiclogin.dto.user.request.UpdateRole;
import com.christianrondon.basiclogin.dto.user.response.UserResponse;
import com.christianrondon.basiclogin.entity.Role;
import com.christianrondon.basiclogin.entity.User;
import com.christianrondon.basiclogin.enums.RoleEnum;
import com.christianrondon.basiclogin.mapper.UserMapper;
import com.christianrondon.basiclogin.repository.RoleRepository;
import com.christianrondon.basiclogin.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> showAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse createUser(CreateUser request) {
        User user = userMapper.toEntity(request);

        Role roleDefault = roleRepository.findByName(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Role not Found"));

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(roleDefault);


        User userCreated = userRepository.save(user);
        return userMapper.toUserResponse(userCreated);
    }

    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID: " + id + " not Found"));

        return userMapper.toUserResponse(user);
    }

    public void deleteUserById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User ID not found"));

        userRepository.deleteById(id);
    }

    public UserResponse updateUserRole(UpdateRole request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User ID not found"));

        Role role = roleRepository.findByName(request.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);

        userRepository.save(user);

        return userMapper.toUserResponse(user);

    }


}
