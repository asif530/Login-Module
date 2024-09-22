package com.module.login.Controller;

import com.module.login.DTO.RegistrationResponse;
import com.module.login.DTO.UserRegistrationDto;
import com.module.login.Service.UserRegistration.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserRegistrationController {

    UserRegistrationService userRegistrationService;

    @PostMapping("registerUser")
    public RegistrationResponse registerUser(@RequestBody UserRegistrationDto userRegistrationDto){
        return userRegistrationService.registerUserBasedOnMedium(userRegistrationDto);
    }
}
