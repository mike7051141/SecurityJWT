package com.springboot.securityjwt.repository;

import com.springboot.securityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User getByUid(String uid);
}