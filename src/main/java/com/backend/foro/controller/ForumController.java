package com.backend.foro.controller;

import com.backend.foro.model.Topic;
import com.backend.foro.model.Comment;
import com.backend.foro.service.ForumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forum")
public class ForumController {

    private final ForumService service;

    public ForumController(ForumService service) {
        this.service = service;
    }

    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.ok(service.getAllTopics());
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        return service.getTopicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Topic>> searchTopics(@RequestParam String q) {
        return ResponseEntity.ok(service.searchTopics(q));
    }

    @PostMapping("/topics")
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        return ResponseEntity.ok(service.createTopic(topic));
    }

    @PostMapping("/topics/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        return ResponseEntity.ok(service.addComment(id, comment));
    }

    @DeleteMapping("/topics/{id}")
public ResponseEntity<String> deleteTopic(@PathVariable Long id) {
    service.deleteTopic(id); // Llama al método del service
    return ResponseEntity.ok("El tema con ID " + id + " fue eliminado correctamente.");
}


}
