package com.module.login.Service.UserRegistration;

import com.module.login.DTO.RegistrationResponse;
import com.module.login.DTO.UserRegistrationDto;
import com.module.login.Dummy.Cognito.CreateUseCognito;
import com.module.login.Dummy.TokenGenerator.GenerateToken;
import com.module.login.ENUM.MediumEnum;
import com.module.login.Entity.Dummy.CognitoUser;
import com.module.login.Entity.UserContactDeviceTable;
import com.module.login.Entity.UserTable;
import com.module.login.Repository.UserRegistrationRepository;
import com.module.login.Service.DeviceContactService.DeviceContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRegistrationService {
    UserRegistrationRepository userRegistrationRepository;
    UserRegistrationHelper userRegistrationHelper;
    DeviceContactService deviceContactService;
    CreateUseCognito createUseCognito;
    GenerateToken generateToken;

    public RegistrationResponse registerUserBasedOnMedium(UserRegistrationDto userRegistrationDto){
        RegistrationResponse registrationResponse = null;

        if(MediumEnum.EMAIL.getId().equals(userRegistrationDto.getMedium())){
            registrationResponse = generateEmailUserToken(userRegistrationDto);
        }
        return registrationResponse;
    }

    public UserTable registerUser(UserRegistrationDto userRegistrationDto){
        UserTable userTable = userRegistrationHelper.convertDtoToEntity(userRegistrationDto);
        return userRegistrationRepository.save(userTable);
    }

    private RegistrationResponse generateEmailUserToken(UserRegistrationDto userRegistrationDto){
        RegistrationResponse registrationResponse = null;
        String emailExistence = "Existing Email and Existing Device";

        //Check if the email exists
        //Get List of all the device with this mail
        List<UserContactDeviceTable> findAllByContact = deviceContactService.findAllByContact(userRegistrationDto.getEmail());

        //List Empty => New Email => create cognito user id => map user with device => generate token
        //List not empty => check if there is mapping => no mapping(device was not used to log in) => create mapping => return token
        if(findAllByContact.isEmpty()){
            emailExistence = "New Email & New Device";
            //create cognito user id
            CognitoUser cognitoUser = createUseCognito.createCognitoUser(MediumEnum.EMAIL.getMedium(),userRegistrationDto.getEmail());

            //create a new user
            UserTable userTable = registerUser(UserRegistrationDto.builder()
                    .cogId(cognitoUser.getCognito_id())
                    .medium(MediumEnum.EMAIL.getId())
                    .build());
            // No need to create device. As device is already created by register device. Just map email with device

            //map user with device
            UserContactDeviceTable userContactDeviceTable = mapDeviceWithContact(userRegistrationDto,userTable);

            // The new mapping is added
            findAllByContact.add(userContactDeviceTable);
        }

        //check if there is mapping
        UserContactDeviceTable userContactDeviceTable = new UserContactDeviceTable();
        for (UserContactDeviceTable usrCntDvcTbl:findAllByContact){
            if(usrCntDvcTbl.getDeviceId().equalsIgnoreCase(userRegistrationDto.getDn())
                    && usrCntDvcTbl.getContact().equalsIgnoreCase(userRegistrationDto.getEmail())){
                userContactDeviceTable = usrCntDvcTbl;
            }
        }
        /*UserContactDeviceTable userContactDeviceTable = (UserContactDeviceTable)
                findAllByContact.stream().filter(x->x.getDeviceId().equalsIgnoreCase(userRegistrationDto.getDn())
                        && x.getContact().equalsIgnoreCase(userRegistrationDto.getEmail()));*/

        if(userContactDeviceTable.getId()==null){
            emailExistence = "Existing Email & New Device";
            Long userTableId = findAllByContact.get(0).getUserTable().getId();
            UserTable userTable = userRegistrationRepository.findById(userTableId).get();
            mapDeviceWithContact(userRegistrationDto,userTable);
        }

        //generate token
        registrationResponse = generateToken.generateToke(userRegistrationDto.getEmail(),MediumEnum.EMAIL.getId());
        registrationResponse.setNewOrExisting(emailExistence);
        registrationResponse.setDn(userRegistrationDto.getDn());

        return registrationResponse;
    }

    private UserContactDeviceTable mapDeviceWithContact(UserRegistrationDto userRegistrationDto, UserTable userTable){
        UserContactDeviceTable userContactDeviceTable = UserContactDeviceTable.builder()
                .contact(userRegistrationDto.getEmail())
                .deviceId(userRegistrationDto.getDn())
                .userTable(userTable)
                .build();
        return deviceContactService.mapDeviceWithContact(userContactDeviceTable);
    }
}
