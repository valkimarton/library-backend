package hu.bme.szoftarch.library.libbackend.service;

import hu.bme.szoftarch.library.libbackend.model.LibUser;
import hu.bme.szoftarch.library.libbackend.model.Writing;
import hu.bme.szoftarch.library.libbackend.model.enums.Category;
import hu.bme.szoftarch.library.libbackend.repository.UserRepository;
import hu.bme.szoftarch.library.libbackend.repository.WritingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WritingService {

    @Autowired
    private WritingRepository writingRepository;

    @Autowired
    private UserRepository userRepository;

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
    public List<Writing> getWritingsByTitleSort(String titlePart) {return writingRepository.findByTitleContainsIgnoreCase(titlePart); }

    @Transactional
    public List<Writing> getWritingsByAuthorId(Long authorId) { return writingRepository.findByAuthorId(authorId); }

    @Transactional
    public List<Writing> getRecommendedWritings(Long userId) {  // TODO: userId parameter should be read from security context
        return getRecommendedWritingsForUser(userId);
    }

    @Transactional
    public Writing updateWriting(Long id, Writing writing) {
        Writing existingWriting = writingRepository.findById(id).orElse(new Writing());
        BeanUtils.copyProperties(writing, existingWriting);
        return writingRepository.saveAndFlush(existingWriting);
    }

    @Transactional
    public void deleteWriting(Long id) { writingRepository.deleteById(id); }

    private List<Writing> getRecommendedWritingsForUser(Long userId) {

        int numberOfRecommendedWritings = 3;

        LibUser user = userRepository.getOne(userId);

        List<Writing> writingsNotRead = getWritings();
        writingsNotRead.removeAll(user.getBooksRead());
        if (writingsNotRead.size() <= numberOfRecommendedWritings)
            return writingsNotRead;

        int numberOfBooksRead = user.getBooksRead().size();

        // Calculating points for Writings based on author and category.

        HashMap<Long, Integer> writingsByAuthor = new HashMap<>();
        HashMap<Category, Integer> writingsByCategory = new HashMap<>();

        for (Writing writing : user.getBooksRead()) {
            Long authorId = writing.getAuthor().getId();
            if(writingsByAuthor.containsKey(authorId))
                writingsByAuthor.put(authorId, writingsByAuthor.get(authorId) + 1);
            else
                writingsByAuthor.put(authorId, 1);

            Category category = writing.getCategory();
            if(writingsByCategory.containsKey(category))
                writingsByCategory.put(category, writingsByCategory.get(category) + 1);
            else
                writingsByCategory.put(category, 1);
        }

        // Creating a HashMap: <WritingId, points>
        HashMap<Long, Double> writingPoints = new HashMap<>();
        for (Writing writing : writingsNotRead) {
            Long authorId = writing.getAuthor().getId();
            Category category = writing.getCategory();
            double authorPoint = writingsByAuthor.containsKey(authorId) ? writingsByAuthor.get(authorId)/numberOfBooksRead : 0;
            double categoryPoint = writingsByCategory.containsKey(category) ? writingsByCategory.get(category)/numberOfBooksRead : 0;
            writingPoints.put(writing.getId(), authorPoint + categoryPoint);
        }

        // Adding the writings with most points to a list
        ArrayList<Writing> recommendedWritings = new ArrayList<>();
        for(int i = 0; i < numberOfRecommendedWritings; ++i) {
            Long mostPointsWritingId = getWritingWithMostPoints(writingPoints);
            Writing writingWithMostPoints = getWritingById(mostPointsWritingId);
            recommendedWritings.add(writingWithMostPoints);
            writingPoints.remove(mostPointsWritingId);
        }

        return recommendedWritings;
    }

    private Long getWritingWithMostPoints(HashMap<Long, Double> writingPoints) {
        Map.Entry<Long, Double> maxEntry = null;
        for (Map.Entry<Long, Double> entry : writingPoints.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue())
            {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }
}
