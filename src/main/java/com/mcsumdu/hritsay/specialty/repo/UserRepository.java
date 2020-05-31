package com.mcsumdu.hritsay.specialty.repo;

import com.mcsumdu.hritsay.specialty.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
