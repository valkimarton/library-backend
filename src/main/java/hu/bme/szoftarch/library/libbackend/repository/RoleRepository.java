package hu.bme.szoftarch.library.libbackend.repository;

import hu.bme.szoftarch.library.libbackend.model.Role;
import hu.bme.szoftarch.library.libbackend.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType roleName);
}
