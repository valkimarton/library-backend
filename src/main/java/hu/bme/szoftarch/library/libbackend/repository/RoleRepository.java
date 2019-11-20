package hu.bme.szoftarch.library.libbackend.repository;

import hu.bme.szoftarch.library.libbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
