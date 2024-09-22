package com.module.login.Repository.Dummy;

import com.module.login.Entity.Dummy.CognitoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CognitoRepository extends JpaRepository<CognitoUser,String > {
}
