package com.timeactuall.demo.security.repository;

import com.timeactuall.demo.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
