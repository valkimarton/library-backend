package hu.bme.szoftarch.library.libbackend.controller;

import hu.bme.szoftarch.library.libbackend.model.Writing;
import hu.bme.szoftarch.library.libbackend.service.WritingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/writing")
public class WritingConrtoller {
    @Autowired
    private WritingService writingService;

    @GetMapping
    public List<Writing> getWritings() {
        return writingService.getWritings();
    }

    @GetMapping("{id}")
    public Writing getWriting(@PathVariable Long id) {
        return writingService.getWritingById(id);
    }

    @PostMapping
    public Writing createWriting(@RequestBody Writing writing) {
        return writingService.createWriting(writing);
    }

    @DeleteMapping(value = "{id}")
    public void deleteWriting(@PathVariable Long id) {
        writingService.deleteWriting(id);
    }

    @PutMapping(value = "{id}")
    public Writing updateWriting(@PathVariable Long id, @NotNull @RequestBody Writing writing) {
        return writingService.updateWriting(id, writing);
    }
}
