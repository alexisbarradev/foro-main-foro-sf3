package com.backend.foro.repository;

import com.backend.foro.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByTitleContainingIgnoreCase(String keyword);
}
