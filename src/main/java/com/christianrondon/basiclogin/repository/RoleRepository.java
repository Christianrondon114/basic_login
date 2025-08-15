package com.christianrondon.basiclogin.repository;

import com.christianrondon.basiclogin.entity.Role;
import com.christianrondon.basiclogin.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
