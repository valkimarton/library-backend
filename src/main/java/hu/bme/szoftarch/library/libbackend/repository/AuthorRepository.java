package hu.bme.szoftarch.library.libbackend.repository;

import hu.bme.szoftarch.library.libbackend.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
