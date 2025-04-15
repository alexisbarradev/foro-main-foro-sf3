package com.backend.foro.controller;

import com.backend.foro.model.Comment;
import com.backend.foro.model.Topic;
import com.backend.foro.repository.CommentRepository;
import com.backend.foro.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin(origins = "http://localhost:4200") // Asegura acceso desde Angular
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TopicRepository topicRepository;

    // GET: Obtener comentarios de un topic
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsByTopic(@PathVariable Long id) {
        Topic topic = topicRepository.findById(id).orElse(null);
        if (topic == null) {
            return ResponseEntity.notFound().build();
        }
        List<Comment> comments = commentRepository.findByTopic(topic);
        return ResponseEntity.ok(comments);
    }

    // POST: Agregar un comentario a un topic
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addCommentToTopic(@PathVariable Long id, @RequestBody Comment comment) {
        Topic topic = topicRepository.findById(id).orElse(null);
        if (topic == null) {
            return ResponseEntity.notFound().build();
        }
        comment.setTopic(topic);
        Comment saved = commentRepository.save(comment);
        return ResponseEntity.ok(saved);
    }
}
