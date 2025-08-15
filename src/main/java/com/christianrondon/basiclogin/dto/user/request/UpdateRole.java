package com.christianrondon.basiclogin.dto.user.request;

import com.christianrondon.basiclogin.enums.RoleEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRole {
    @NotNull
    private Long userId;
    @NotNull
    private RoleEnum roleName;
}
