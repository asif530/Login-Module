package com.module.login.Entity;

import com.module.login.ENUM.ActivityStatusEnum;
import com.module.login.ENUM.MediumEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_table")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_table_seq", allocationSize = 1)
    @Column(name = "usr_tbl_id")
    private Long id;

    @Column(name = "cog_id", nullable = false)
    private String cogId;

    @Column(name = "medium", nullable = false)
    private Integer medium;

    @Column(name = "login_logout_status", nullable = false)
    private Boolean loginLogoutStatus = Boolean.FALSE;

    @Column(name = "activity_status", nullable = false)
    private Integer activityStatus = ActivityStatusEnum.REGULAR.getId();
}
