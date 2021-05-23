package com.adedotunalausa.week9taskadedotunalausa.repository;

import com.adedotunalausa.week9taskadedotunalausa.model.ERole;
import com.adedotunalausa.week9taskadedotunalausa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
