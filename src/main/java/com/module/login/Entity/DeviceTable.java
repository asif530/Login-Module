package com.module.login.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "device_table")
@Builder
//@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_seq")
    @SequenceGenerator(name = "device_seq", sequenceName = "device_table_seq", allocationSize = 1)
    @Column(name = "dvc_tbl_id")
    private Long id;

    @Column(name = "dn", nullable = false)
    private String dn;

    @Column(name = "dvc_cog_id", nullable = false)
    private String dvcCogId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_tbl_id", referencedColumnName = "usr_tbl_id")
    private UserTable userTable;

}
