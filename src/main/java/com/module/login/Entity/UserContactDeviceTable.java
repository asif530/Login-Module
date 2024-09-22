package com.module.login.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_contact_device_table")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserContactDeviceTable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_contact_device_seq")
    @SequenceGenerator(name = "user_contact_device_seq", sequenceName = "user_contact_device_table_seq", allocationSize = 1)
    @Column(name = "u_c_d_id")
    private Long id;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_tbl_id", referencedColumnName = "usr_tbl_id")
    private UserTable userTable;
}
