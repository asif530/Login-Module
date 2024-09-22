package com.module.login.DTO;

import com.module.login.ENUM.ActivityStatusEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.NonNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    private Long id;
    @Email
    private String email;
    private String phoneNumber;
    private Integer medium;
    private String cogId;
    @Builder.Default
    private Boolean loginLogoutStatus = Boolean.FALSE;
    @Builder.Default
    private Integer activityStatus = ActivityStatusEnum.REGULAR.getId();
    @NotNull
    @NotEmpty
    private String dn;
}
