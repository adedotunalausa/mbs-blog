package com.adedotunalausa.mbsblog.repository;

import com.adedotunalausa.mbsblog.model.ERole;
import com.adedotunalausa.mbsblog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
