package com.module.login.Dummy.Cognito;

import com.module.login.ENUM.MediumEnum;
import com.module.login.Entity.Dummy.CognitoUser;
import com.module.login.Repository.Dummy.CognitoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateUseCognito {
    CognitoRepository cognitoRepository;
    private static String device_cognito_prefix = "dvc-usr-cog-";
    private static String email_cognito_prefix = "mail-usr-cog-";
    public CognitoUser createCognitoUser(String userType, String user) {
        CognitoUser cognitoUser = new CognitoUser();

        if (MediumEnum.DEVICE.getMedium().equalsIgnoreCase(userType)) {
             cognitoUser =
                    CognitoUser.builder().cognito_id(device_cognito_prefix.concat(user)).build();
        }

        if (MediumEnum.EMAIL.getMedium().equalsIgnoreCase(userType)) {
            cognitoUser =
                    CognitoUser.builder().cognito_id(email_cognito_prefix.concat(user)).build();
        }

        cognitoUser = cognitoRepository.save(cognitoUser);

        return cognitoUser;
    }


}
