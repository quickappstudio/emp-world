package com.qas.empworld.repository;

import com.qas.empworld.model.UserMaster;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserMaster, String> {
    UserMaster save(UserMaster userMaster);
    UserMaster findByUserId(String  userId);
    UserMaster findByLoginId(String loginId);
}
