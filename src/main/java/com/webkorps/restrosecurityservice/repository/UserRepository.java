package com.webkorps.restrosecurityservice.repository;

import com.webkorps.restrosecurityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

}
