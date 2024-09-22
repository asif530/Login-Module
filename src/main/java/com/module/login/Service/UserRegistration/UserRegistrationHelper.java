package com.module.login.Service.UserRegistration;

import com.module.login.DTO.UserRegistrationDto;
import com.module.login.Entity.UserTable;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationHelper {
    public UserTable convertDtoToEntity(UserRegistrationDto userRegistrationDto){
        UserTable userTable = new UserTable();
        BeanUtils.copyProperties(userRegistrationDto,userTable );
        return userTable;
    }
}
