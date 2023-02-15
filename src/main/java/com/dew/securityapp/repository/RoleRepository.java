package com.dew.securityapp.repository;

import com.dew.securityapp.entity.Role;
import com.dew.securityapp.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
