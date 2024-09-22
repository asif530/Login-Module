package com.module.login.Service.DeviceRegistration;

import com.module.login.DTO.DeviceRegistrationDto;
import com.module.login.Entity.DeviceTable;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DeviceRegistrationHelper {

    public DeviceTable convertDtoToEntity(DeviceRegistrationDto deviceRegistrationDto){
        DeviceTable deviceTable = new DeviceTable();
        BeanUtils.copyProperties(deviceRegistrationDto,deviceTable);
        return deviceTable;
    }

    public DeviceRegistrationDto convertEntityToDto(DeviceTable deviceTable){
        DeviceRegistrationDto deviceRegistrationDto = new DeviceRegistrationDto();
        BeanUtils.copyProperties(deviceTable, deviceRegistrationDto);
        return deviceRegistrationDto;
    }
}
