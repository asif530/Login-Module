package com.module.login.Repository;

import com.module.login.Entity.UserTable;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//JpaAttributeConverter ???????
@Repository
public interface UserRegistrationRepository extends JpaRepository<UserTable,Long> {

}
