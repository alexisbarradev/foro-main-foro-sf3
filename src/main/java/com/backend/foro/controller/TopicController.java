package com.backend.foro.controller;

import com.backend.foro.model.Category;
import com.backend.foro.model.CategoryName;
import com.backend.foro.model.Topic;
import com.backend.foro.repository.CategoryRepository;
import com.backend.foro.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin(origins = "http://localhost:4200")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // ✅ Crear topic con ID de categoría
    @PostMapping("/create")
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic, @RequestParam Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        topic.setCategory(category);
        Topic saved = topicRepository.save(topic);
        return ResponseEntity.ok(saved);
    }

    // ✅ Obtener todos los topics
    @GetMapping
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    // GET /api/topics/by-category/{name}
@GetMapping("/by-category/{name}")
public ResponseEntity<List<Topic>> getTopicsByCategory(@PathVariable String name) {
    try {
        Category category = categoryRepository.findByName(Enum.valueOf(CategoryName.class, name.toUpperCase()))
                .orElse(null);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        List<Topic> topics = topicRepository.findByCategory(category);
        return ResponseEntity.ok(topics);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().build(); // por si el nombre no es válido
    }
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
    if (!topicRepository.existsById(id)) {
        return ResponseEntity.notFound().build();
    }

    topicRepository.deleteById(id);
    return ResponseEntity.noContent().build();
}


}
