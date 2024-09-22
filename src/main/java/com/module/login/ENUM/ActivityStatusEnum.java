package com.module.login.ENUM;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActivityStatusEnum {
    REGULAR(1,"Regular"),
    TEMP_BAN(2,"Temporary Ban"),
    PERMA_BAN(3,"Permanent Ban");

    final Integer id;
    final String activityStatus;

}
