package com.backend.foro.repository;

import com.backend.foro.model.Category;
import com.backend.foro.model.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(CategoryName name);
    Optional<Category> findByName(CategoryName name);
}
