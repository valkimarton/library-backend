package hu.bme.szoftarch.library.libbackend.controller;

import hu.bme.szoftarch.library.libbackend.dto.BookDTO;
import hu.bme.szoftarch.library.libbackend.dto.WritingDTO;
import hu.bme.szoftarch.library.libbackend.model.Book;
import hu.bme.szoftarch.library.libbackend.model.Writing;
import hu.bme.szoftarch.library.libbackend.service.WritingService;
import hu.bme.szoftarch.library.libbackend.utils.DTOConverter;
import hu.bme.szoftarch.library.libbackend.utils.exceptions.IllegalDeleteRequestException;
import hu.bme.szoftarch.library.libbackend.utils.exceptions.IllegalUpdateRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/writing")
public class WritingConrtoller {
    @Autowired
    private WritingService writingService;

    @Autowired
    private DTOConverter dtoConverter;

    private static final Logger logger = LoggerFactory.getLogger(WritingConrtoller.class);

    @GetMapping
    public List<WritingDTO> getWritings() {
        List<Writing> writings = writingService.getWritings();
        logger.warn("GET: api/writing SUCCESS");
        return dtoConverter.toWritingDTOList(writings);
    }

    @GetMapping("{id}")
    public WritingDTO getWriting(@PathVariable Long id) {
        Writing writing = writingService.getWritingById(id);
        return dtoConverter.toWritingDTO(writing);
    }

    @GetMapping("sort/title/{titlepart}")
    public List<WritingDTO> getWritingByTitleSort(@PathVariable String titlepart) {
        List<Writing> writings = writingService.getWritingsByTitleSort(titlepart);
        return dtoConverter.toWritingDTOList(writings);
    }

    @GetMapping("sort/author/{id}")
    public List<WritingDTO> getWritingByTitleSort(@PathVariable Long id) {
        List<Writing> writings = writingService.getWritingsByAuthorId(id);
        return dtoConverter.toWritingDTOList(writings);
    }

    @GetMapping("recommend/{id}")
    public List<WritingDTO> getRecommendedWritings(@PathVariable Long id) {
        List<Writing> writings = writingService.getRecommendedWritings(id);
        return dtoConverter.toWritingDTOList(writings);
    }

    @GetMapping("{id}/books")
    public List<BookDTO> getBooks(@PathVariable Long id) {
        List<Book> books = writingService.getBooksForWriting(id);
        return dtoConverter.toBookDTOList(books);
    }

    @GetMapping("{id}/books_available")
    public List<BookDTO> getAvailableBooks(@PathVariable Long id) {
        List<Book> availableBooks = writingService.getAvailableBooksForWriting(id);
        return dtoConverter.toBookDTOList(availableBooks);
    }

    @PostMapping
    public WritingDTO createWriting(@RequestBody WritingDTO writingDTO) throws ParseException {
        Writing writing = dtoConverter.toWriting(writingDTO);
        Writing writingCreated = writingService.createWriting(writing);
        return dtoConverter.toWritingDTO(writingCreated);
    }

    @DeleteMapping(value = "{id}")
    public void deleteWriting(@PathVariable Long id) {

        try {
            writingService.deleteWriting(id);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new IllegalDeleteRequestException("Can not remove writing, since there are books referencing it");
        }
    }

    @PutMapping(value = "{id}")
    public WritingDTO updateWriting(@PathVariable Long id, @NotNull @RequestBody WritingDTO writingDTO) throws ParseException {
        try {
            Writing writing = dtoConverter.toWriting(writingDTO);
            Writing writingUpdated =  writingService.updateWriting(id, writing);
            return dtoConverter.toWritingDTO(writingUpdated);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new IllegalUpdateRequestException("No author found with the given ID, thus it can not be set as author of writing.");
        }
    }
}
