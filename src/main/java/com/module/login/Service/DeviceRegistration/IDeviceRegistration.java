package com.module.login.Service.DeviceRegistration;

import com.module.login.DTO.DeviceRegistrationDto;
import com.module.login.DTO.RegistrationResponse;

public interface IDeviceRegistration {
    public RegistrationResponse deviceRegistration(DeviceRegistrationDto deviceRegistrationDto);
}
