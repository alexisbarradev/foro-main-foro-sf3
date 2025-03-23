package com.backend.foro.service;

import com.backend.foro.model.Topic;
import com.backend.foro.model.Comment;
import com.backend.foro.repository.TopicRepository;
import com.backend.foro.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumService {
    private final TopicRepository topicRepo;
    private final CommentRepository commentRepo;

    public ForumService(TopicRepository topicRepo, CommentRepository commentRepo) {
        this.topicRepo = topicRepo;
        this.commentRepo = commentRepo;
    }

    public List<Topic> getAllTopics() {
        return topicRepo.findAll();
    }

    public Optional<Topic> getTopicById(Long id) {
        return topicRepo.findById(id);
    }

    public List<Topic> searchTopics(String keyword) {
        return topicRepo.findByTitleContainingIgnoreCase(keyword);
    }

    public Topic createTopic(Topic topic) {
        return topicRepo.save(topic);
    }

    public Comment addComment(Long topicId, Comment comment) {
        Topic topic = topicRepo.findById(topicId).orElseThrow();
        comment.setTopic(topic);
        return commentRepo.save(comment);
    }

    // ✅ Método nuevo
    public void deleteTopic(Long id) {
        if (topicRepo.existsById(id)) {
            topicRepo.deleteById(id);
        } else {
            throw new RuntimeException("Topic not found");
        }
    }
}
