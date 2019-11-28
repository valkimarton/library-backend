package hu.bme.szoftarch.library.libbackend.controller;

import hu.bme.szoftarch.library.libbackend.dto.BookDTO;
import hu.bme.szoftarch.library.libbackend.model.Book;
import hu.bme.szoftarch.library.libbackend.service.BookService;
import hu.bme.szoftarch.library.libbackend.service.WritingService;
import hu.bme.szoftarch.library.libbackend.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private WritingService writingService;

    @Autowired
    private DTOConverter dtoConverter;

    @GetMapping
    public List<BookDTO> getBooks() {
        List<Book> books = bookService.getBooks();
        return dtoConverter.toBookDTOList(books);
    }

    @GetMapping("{id}")
    public BookDTO getBook(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return dtoConverter.toBookDTO(book);
    }

    @PostMapping("{writingId}")
    public BookDTO createBook(@PathVariable Long writingId) {
        Book createdBook = bookService.createBook(writingId);
        return dtoConverter.toBookDTO(createdBook);
    }

    @PostMapping("borrow/{writingId}")
    public BookDTO borrowBook(@PathVariable Long writingId) {
        Book borrowedBook = bookService.lendBook(writingId);
        return dtoConverter.toBookDTO(borrowedBook);
    }

    @PostMapping("return/{bookId}")
    public BookDTO returnBook(@PathVariable Long bookId) {
        Book returnedBook = bookService.returnBook(bookId);
        return dtoConverter.toBookDTO(returnedBook);
    }

}
