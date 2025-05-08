package com.example.demo.repositoies;

import com.example.demo.enity.AppRole;
import com.example.demo.enity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}