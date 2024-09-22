package com.module.login.Repository;

import com.module.login.Entity.DeviceTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRegistrationRepository extends JpaRepository<DeviceTable,Long> {
        Optional<DeviceTable> findByDn(String dn);
}
