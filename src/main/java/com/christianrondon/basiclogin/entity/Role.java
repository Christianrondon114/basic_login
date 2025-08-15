package com.christianrondon.basiclogin.entity;

import com.christianrondon.basiclogin.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleEnum name;

    public Role(RoleEnum roleEnum) {
        this.name = roleEnum;
    }
}
