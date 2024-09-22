package com.module.login.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {
    private String dn;
    private String email;
    private String token;
    @Builder.Default
    private String authType="Bearer";
    private String newOrExisting;
}
