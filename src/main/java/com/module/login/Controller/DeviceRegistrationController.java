package com.module.login.Controller;

import com.module.login.DTO.DeviceRegistrationDto;
import com.module.login.DTO.RegistrationResponse;
import com.module.login.Service.DeviceRegistration.IDeviceRegistration;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("device")
@AllArgsConstructor
public class DeviceRegistrationController {

    IDeviceRegistration deviceRegistration;

    @PostMapping("registerDevice")
    public RegistrationResponse registerDevice(@RequestBody DeviceRegistrationDto deviceRegistrationDto){
        return deviceRegistration.deviceRegistration(deviceRegistrationDto);
    }
}
