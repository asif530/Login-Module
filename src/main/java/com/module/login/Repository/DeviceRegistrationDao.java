package com.module.login.Repository;

import com.module.login.Entity.DeviceTable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class DeviceRegistrationDao {

    DeviceRegistrationRepository deviceRegistrationRepository;

    public Boolean checkDeviceExistence(String device_number){
        Boolean deviceExistence = deviceRegistrationRepository.findByDn(device_number).isEmpty()?Boolean.FALSE:Boolean.TRUE;
        return  deviceExistence;
    }

    public DeviceTable registerDevice(DeviceTable deviceTable){
        return deviceRegistrationRepository.save(deviceTable);
    }

}
