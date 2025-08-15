package com.christianrondon.basiclogin.dto.user.response;

import com.christianrondon.basiclogin.entity.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private Long roleId;
    private String roleName;

}
