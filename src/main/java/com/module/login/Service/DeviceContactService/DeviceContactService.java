package com.module.login.Service.DeviceContactService;

import com.module.login.DTO.UserRegistrationDto;
import com.module.login.Entity.UserContactDeviceTable;
import com.module.login.Repository.DeviceContactMappingRepository.DeviceContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeviceContactService {
    DeviceContactRepository deviceContactRepository;

    public List<UserContactDeviceTable> findAllByContact(String contact){
        Optional<List<UserContactDeviceTable>> allContact = deviceContactRepository.findAllByContact(contact);

        return allContact.orElseGet(ArrayList::new);
    }

    public UserContactDeviceTable mapDeviceWithContact(UserContactDeviceTable userContactDeviceTable){
        return deviceContactRepository.save(userContactDeviceTable);
    }
}
