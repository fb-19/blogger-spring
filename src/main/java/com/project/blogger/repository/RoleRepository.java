package com.project.blogger.repository;

import com.project.blogger.model.Role;
import com.project.blogger.model.enums.Roles;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(Roles name);

}
