package hu.bme.szoftarch.library.libbackend.repository;

import hu.bme.szoftarch.library.libbackend.model.LibUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<LibUser, Long> {
    Optional<LibUser> findByUsername(String username);
}
