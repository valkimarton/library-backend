package hu.bme.szoftarch.library.libbackend.repository;

import hu.bme.szoftarch.library.libbackend.model.Writing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WritingRepository extends JpaRepository<Writing, Long> {
    List<Writing> findByTitleContainsIgnoreCase(String titlePart);
    List<Writing> findByAuthorId(Long authorId);
}
