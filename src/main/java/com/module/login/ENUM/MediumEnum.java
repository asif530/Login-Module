package com.module.login.ENUM;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MediumEnum {
    DEVICE(1,"Device"),
    EMAIL(2,"Email"),
    PHONE(3,"Phone Number");

    final Integer id;
    final String medium;
}
