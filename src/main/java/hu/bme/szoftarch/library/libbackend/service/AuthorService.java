package hu.bme.szoftarch.library.libbackend.service;

import hu.bme.szoftarch.library.libbackend.model.Author;
import hu.bme.szoftarch.library.libbackend.repository.AuthorRepository;
import hu.bme.szoftarch.library.libbackend.utils.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public Author createAuthor(Author author) {
        if (author.getBooks() == null)
            author.setBooks(new ArrayList<>());
        return authorRepository.saveAndFlush(author);
    }

    @Transactional
    public Author getAuthorById(Long id) { return authorRepository.getOne(id); }

    @Transactional
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Transactional
    public Author updateAuthor(Long id, Author author) {
        Author existingAuthor = authorRepository.findById(id).orElse(new Author());
        NullAwareBeanUtils.copyNonNullProperties(author, existingAuthor);
        return authorRepository.saveAndFlush(existingAuthor);
    }

    @Transactional
    public void deleteAuthor(Long id) { authorRepository.deleteById(id); }
}
