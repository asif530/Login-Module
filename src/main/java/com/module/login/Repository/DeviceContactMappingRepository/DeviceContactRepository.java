package com.module.login.Repository.DeviceContactMappingRepository;

import com.module.login.Entity.UserContactDeviceTable;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Module.Open -> Aspectj????????
@Repository
public interface DeviceContactRepository extends JpaRepository<UserContactDeviceTable,Long> {
    Optional<List<UserContactDeviceTable>> findAllByContact(String contact);

}
