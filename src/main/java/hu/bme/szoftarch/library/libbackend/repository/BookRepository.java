package hu.bme.szoftarch.library.libbackend.repository;

import hu.bme.szoftarch.library.libbackend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
