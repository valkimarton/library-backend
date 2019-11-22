package hu.bme.szoftarch.library.libbackend.service;

import hu.bme.szoftarch.library.libbackend.model.Book;
import hu.bme.szoftarch.library.libbackend.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Book createBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    @Transactional
    public Book getBookById(Long id) { return bookRepository.getOne(id); }

    @Transactional
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id).orElse(new Book());
        BeanUtils.copyProperties(book, existingBook);
        return bookRepository.saveAndFlush(existingBook);
    }

    @Transactional
    public void deleteBook(Long id) { bookRepository.deleteById(id); }
}
