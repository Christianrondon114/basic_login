package com.christianrondon.basiclogin.service;

import com.christianrondon.basiclogin.entity.Role;
import com.christianrondon.basiclogin.enums.RoleEnum;
import com.christianrondon.basiclogin.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
}
