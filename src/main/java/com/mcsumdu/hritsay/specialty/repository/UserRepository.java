package com.mcsumdu.hritsay.specialty.repository;

import com.mcsumdu.hritsay.specialty.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
