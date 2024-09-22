package com.module.login.Entity.Dummy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "cognito_user")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CognitoUser {
    @Id
    @Column(name = "cognito_user_id")
    private String cognito_id;
}
