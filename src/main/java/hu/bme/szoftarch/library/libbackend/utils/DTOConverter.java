package hu.bme.szoftarch.library.libbackend.utils;

import hu.bme.szoftarch.library.libbackend.dto.AuthorDTO;
import hu.bme.szoftarch.library.libbackend.dto.BookDTO;
import hu.bme.szoftarch.library.libbackend.dto.UserDTO;
import hu.bme.szoftarch.library.libbackend.dto.WritingDTO;
import hu.bme.szoftarch.library.libbackend.model.*;
import hu.bme.szoftarch.library.libbackend.model.enums.RoleType;
import hu.bme.szoftarch.library.libbackend.service.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WritingService writingService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public List<WritingDTO> toWritingDTOList(List<Writing> writings) {
        List<WritingDTO> writingDTOs = new ArrayList<>();
        for (Writing writing : writings) {
            writingDTOs.add(toWritingDTO(writing));
        }
        return writingDTOs;
    }

    public WritingDTO toWritingDTO(Writing writing) {
        WritingDTO writingDTO =  modelMapper.map(writing, WritingDTO.class);
        writingDTO.setConcreteBookIds(getBookIds(writing.getConcreteBooks()));
        return writingDTO;
    }

    public Writing toWriting(WritingDTO writingDTO) throws ParseException {
        Writing writing = modelMapper.map(writingDTO, Writing.class);

        writing.setAuthor(authorService.getAuthorById(writingDTO.getAuthorId()));
        writing.setConcreteBooks(getBooksFromBookIdList(writingDTO.getConcreteBookIds()));

        return writing;
    }

    public List<UserDTO> toUserDTOList(List<LibUser> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (LibUser user : users) {
            userDTOs.add(toUserDTO(user));
        }
        return userDTOs;
    }

    public UserDTO toUserDTO(LibUser user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        userDTO.setBookIds(getBookIds(user.getBooks()));
        userDTO.setBooksReadIds(getWritingIds(user.getBooksRead()));
        userDTO.setRoleNames(getRoleNames(user.getRoles()));

        return userDTO;
    }

    public LibUser toUser(UserDTO userDTO) {
        LibUser user = modelMapper.map(userDTO, LibUser.class);

        user.setBooks(getBooksFromBookIdList(userDTO.getBookIds()));
        user.setBooksRead(getWritingsFromWritingIdList(userDTO.getBooksReadIds()));
        user.setRoles(getRolesFromRoleNameList(userDTO.getRoleNames()));

        return user;
    }

    public BookDTO toBookDTO(Book book) {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        bookDTO.setWritingId(book.getWriting().getId());
        return bookDTO;
    }

    public Book toBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);

        book.setWriting(writingService.getWritingById(bookDTO.getWritingId()));
        book.setLendee(userService.getUserById(bookDTO.getLendeeId()));

        return book;
    }

    public List<BookDTO> toBookDTOList(List<Book> books) {
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            bookDTOs.add(toBookDTO(book));
        }
        return bookDTOs;
    }

    public List<AuthorDTO> toAuthorDTOList(List<Author> authors) {
        List<AuthorDTO> authorDTOs = new ArrayList<>();
        for (Author author : authors) {
            authorDTOs.add(toAuthorDTO(author));
        }
        return authorDTOs;
     }

    public AuthorDTO toAuthorDTO(Author author) {
        AuthorDTO authorDTO = modelMapper.map(author, AuthorDTO.class);
        authorDTO.setBookIds(getWritingIds(author.getBooks()));

        return authorDTO;
    }

    public Author toAuthor(AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);

        author.setBooks(getWritingsFromWritingIdList(authorDTO.getBookIds()));

        return author;
    }

    private List<Long> getBookIds(List<Book> books) {
        List<Long> ids = new ArrayList<>();
        for (Book book : books) {
            ids.add(book.getId());
        }
        return ids;
    }

    private List<Long> getWritingIds(List<Writing> writings) {
        List<Long> ids = new ArrayList<>();
        for (Writing writing : writings) {
            ids.add(writing.getId());
        }
        return ids;
    }

    private List<Book> getBooksFromBookIdList(List<Long> bookIds) {
        List<Book> books = new ArrayList<>();
        for (Long id : bookIds) {
            books.add(bookService.getBookById(id));
        }
        return books;
    }

    private List<Writing> getWritingsFromWritingIdList(List<Long> writingIds) {
        List<Writing> writings = new ArrayList<>();
        for (Long id: writingIds) {
            writings.add(writingService.getWritingById(id));
        }
        return writings;
    }

    private List<RoleType> getRoleNames(List<Role> roles) {
        List<RoleType> roleNames = new ArrayList<>();
        for (Role role : roles) {
            roleNames.add(role.getName());
        }
        return roleNames;
    }

    private List<Role> getRolesFromRoleNameList(List<RoleType> roleNames) {
        List<Role> roles = new ArrayList<>();
        for (RoleType roleName : roleNames) {
            roles.add(roleService.findByRoleName(roleName));
        }
        return roles;
    }
}
