package com.mcsumdu.hritsay.specialty.repository;

import com.mcsumdu.hritsay.specialty.entity.SecRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<SecRole, Long> {
}
