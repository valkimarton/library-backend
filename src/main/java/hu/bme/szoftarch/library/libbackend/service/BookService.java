package hu.bme.szoftarch.library.libbackend.service;

import hu.bme.szoftarch.library.libbackend.model.Book;
import hu.bme.szoftarch.library.libbackend.model.LibUser;
import hu.bme.szoftarch.library.libbackend.model.Writing;
import hu.bme.szoftarch.library.libbackend.repository.BookRepository;
import hu.bme.szoftarch.library.libbackend.repository.WritingRepository;
import hu.bme.szoftarch.library.libbackend.utils.exceptions.LibraryException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WritingService writingService;

    @Autowired
    private UserService userService;

    @Transactional
    public Book createBook(Long writingId) {
        Writing writing = writingService.getWritingById(writingId);
        Book createdBook = bookRepository.saveAndFlush(new Book(writing));
        writing.addConcreteBook(createdBook);
        return createdBook;
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

    @Transactional
    public Book lendBook(Long writingId) throws LibraryException {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
           if(username == null || username.equals("anonymousUser"))
            throw new LibraryException("Unauthenticated user can not borrow a book.");
        LibUser user = userService.getUserByUsername(username);

        List<Book> availableBooks = writingService.getAvailableBooksForWriting(writingId);
        if (availableBooks.isEmpty())
            throw new LibraryException("No available books for writing " + writingService.getWritingById(writingId).getTitle());
        Book bookToLend = availableBooks.get(0);

        return bookToLend.lend(user);
    }


}
