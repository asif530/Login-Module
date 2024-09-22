package com.module.login.Service.DeviceRegistration;

import com.module.login.DTO.DeviceRegistrationDto;
import com.module.login.DTO.RegistrationResponse;
import com.module.login.DTO.UserRegistrationDto;
import com.module.login.Dummy.Cognito.CreateUseCognito;
import com.module.login.Dummy.TokenGenerator.GenerateToken;
import com.module.login.ENUM.MediumEnum;
import com.module.login.Entity.DeviceTable;
import com.module.login.Entity.Dummy.CognitoUser;
import com.module.login.Entity.UserTable;
import com.module.login.Repository.DeviceRegistrationDao;
import com.module.login.Service.UserRegistration.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeviceRegistrationService implements IDeviceRegistration{

    DeviceRegistrationDao deviceRegistrationDao;
    DeviceRegistrationHelper deviceRegistrationHelper;
    UserRegistrationService userRegistrationService;
    GenerateToken generateToken;

    CreateUseCognito createUseCognito;
    @Override
    public RegistrationResponse deviceRegistration(DeviceRegistrationDto deviceRegistrationDto) {
        DeviceRegistrationDto deviceRegistrationDto1 = new DeviceRegistrationDto();
        //Check if device exists with deviceId
        Boolean deviceExistence = deviceRegistrationDao.checkDeviceExistence(deviceRegistrationDto.getDn());
        /*
         * false -> create user in cognito -> create user in database ->  register device -> generate token
         * true -> generate token
        **/
        if(!deviceExistence){
            //region create user in cognito
            CognitoUser cognitoUserId = createUseCognito.createCognitoUser(MediumEnum.DEVICE.getMedium(), deviceRegistrationDto.getDn());
            //endregion

            //region create user in database
            UserTable userTable = userRegistrationService.registerUser(UserRegistrationDto.builder()
                                  .cogId(cognitoUserId.getCognito_id())
                                  .medium(MediumEnum.DEVICE.getId())
                                  .build());
            //endregion

            //region register-device
            DeviceTable deviceTable = deviceRegistrationHelper.convertDtoToEntity(deviceRegistrationDto);

            deviceTable.setDvcCogId(cognitoUserId.getCognito_id());
            deviceTable.setUserTable(userTable);

            deviceTable = deviceRegistrationDao.registerDevice(deviceTable);

            deviceRegistrationDto1 = deviceRegistrationHelper.convertEntityToDto(deviceTable);
            //endregion
        }

        RegistrationResponse registrationResponse = generateToken.generateToke(deviceRegistrationDto.getDn(),MediumEnum.DEVICE.getId());
        registrationResponse.setNewOrExisting(deviceExistence?"Existing Device": "New Device");
        return registrationResponse;
    }
}
