package hu.bme.szoftarch.library.libbackend.service;

import hu.bme.szoftarch.library.libbackend.model.Writing;
import hu.bme.szoftarch.library.libbackend.repository.WritingRepository;
import hu.bme.szoftarch.library.libbackend.utils.DaoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WritingService {

    @Autowired
    private WritingRepository writingRepository;

    @Transactional
    public Writing createWriting(Writing writing) {
        return writingRepository.saveAndFlush(writing);
    }

    @Transactional
    public Writing getWritingById(Long id) { return writingRepository.getOne(id); }

    @Transactional
    public List<Writing> getWritings() {
        return writingRepository.findAll();
    }

    @Transactional
    public Writing updateWriting(Long id, Writing writing) {
        Writing existingWriting = writingRepository.findById(id).orElse(new Writing());
        BeanUtils.copyProperties(writing, existingWriting);
        return writingRepository.saveAndFlush(existingWriting);
    }

    @Transactional
    public void deleteWriting(Long id) { writingRepository.deleteById(id); }
}
