package hu.bme.szoftarch.library.libbackend.controller;

import hu.bme.szoftarch.library.libbackend.dto.AuthorDTO;
import hu.bme.szoftarch.library.libbackend.model.Author;
import hu.bme.szoftarch.library.libbackend.service.AuthorService;
import hu.bme.szoftarch.library.libbackend.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private DTOConverter dtoConverter;

    @GetMapping
    private List<AuthorDTO> getAuthors() {
        List<Author> authors = authorService.getAuthors();
        return dtoConverter.toAuthorDTOList(authors);
    }

    @GetMapping("{id}")
    public AuthorDTO getAuthor(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        return dtoConverter.toAuthorDTO(author);
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = dtoConverter.toAuthor(authorDTO);
        Author authorCreated = authorService.createAuthor(author);
        return dtoConverter.toAuthorDTO(authorCreated);
    }

    @PutMapping("{id}")
    public AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        Author author = dtoConverter.toAuthor(authorDTO);
        Author authorUpdated = authorService.updateAuthor(id, author);
        return dtoConverter.toAuthorDTO(authorUpdated);
    }

    @DeleteMapping("{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
