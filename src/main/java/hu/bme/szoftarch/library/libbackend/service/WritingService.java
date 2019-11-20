package hu.bme.szoftarch.library.libbackend.service;

import hu.bme.szoftarch.library.libbackend.model.Writing;
import hu.bme.szoftarch.library.libbackend.repository.WritingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WritingService {

    @Autowired
    private WritingRepository writingRepository;

    public Writing createWriting(Writing writing) { return writingRepository.saveAndFlush(writing); }

    public Writing getWritingById(Long id) { return writingRepository.getOne(id); }

    public List<Writing> getWritings() { return writingRepository.findAll(); }

    public Writing updateWriting(Long id, Writing writing) {
        Writing existingWriting = writingRepository.findById(id).orElse(new Writing());
        BeanUtils.copyProperties(writing, existingWriting);
        return writingRepository.saveAndFlush(existingWriting);
    }

    public void deleteWriting(Long id) { writingRepository.deleteById(id); }
}
