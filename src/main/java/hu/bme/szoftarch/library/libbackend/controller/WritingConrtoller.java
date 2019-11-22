package hu.bme.szoftarch.library.libbackend.controller;

import hu.bme.szoftarch.library.libbackend.dto.WritingDTO;
import hu.bme.szoftarch.library.libbackend.model.Writing;
import hu.bme.szoftarch.library.libbackend.service.WritingService;
import hu.bme.szoftarch.library.libbackend.utils.DaoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/writing")
public class WritingConrtoller {
    @Autowired
    private WritingService writingService;

    @Autowired
    private DaoConverter daoConverter;

    @GetMapping
    public List<WritingDTO> getWritings() {
        List<Writing> writings = writingService.getWritings();
        return daoConverter.toWritingDTOList(writings);
    }

    @GetMapping("{id}")
    public WritingDTO getWriting(@PathVariable Long id) {
        Writing writing = writingService.getWritingById(id);
        return daoConverter.toWritingDTO(writing);
    }

    @PostMapping
    public WritingDTO createWriting(@RequestBody WritingDTO writingDTO) throws ParseException {
        Writing writing = daoConverter.toWriting(writingDTO);
        Writing writingCreated = writingService.createWriting(writing);
        return daoConverter.toWritingDTO(writingCreated);
    }

    @DeleteMapping(value = "{id}")
    public void deleteWriting(@PathVariable Long id) {
        writingService.deleteWriting(id);
    }

    @PutMapping(value = "{id}")
    public WritingDTO updateWriting(@PathVariable Long id, @NotNull @RequestBody WritingDTO writingDTO) throws ParseException {
        Writing writing = daoConverter.toWriting(writingDTO);
        Writing writingUpdated =  writingService.updateWriting(id, writing);
        return daoConverter.toWritingDTO(writingUpdated);
    }
}
