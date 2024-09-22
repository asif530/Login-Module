package com.module.login.Dummy.TokenGenerator;

import com.module.login.DTO.RegistrationResponse;
import com.module.login.ENUM.MediumEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GenerateToken {
    private final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    public RegistrationResponse generateToke(String user, Integer medium){
        RegistrationResponse registrationResponse = null;

        if(Objects.equals(MediumEnum.DEVICE.getId(), medium)){
             registrationResponse = RegistrationResponse.builder()
                    .dn(user).token(token)
                   .build();
        }

        if(Objects.equals(MediumEnum.EMAIL.getId(), medium)){
            registrationResponse = RegistrationResponse.builder()
                    .email(user).token(token)
                    .build();
        }

        return registrationResponse;
    }
}
