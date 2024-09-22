package com.module.login.DTO;

import com.module.login.Entity.UserTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRegistrationDto {

    private Long id;
    private String dn;
    private String dvcCogId;
    private UserTable userTable;

}
