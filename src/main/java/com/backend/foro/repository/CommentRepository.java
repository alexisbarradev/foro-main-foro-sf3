package com.backend.foro.repository;

import com.backend.foro.model.Comment;
import com.backend.foro.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTopic(Topic topic);
}
