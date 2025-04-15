package com.backend.foro.config;


import com.backend.foro.model.CategoryName;
import com.backend.foro.model.Category;

import com.backend.foro.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategoryInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            for (CategoryName name : CategoryName.values()) {
                Category category = new Category();
                category.setName(name);
                categoryRepository.save(category);
            }
            System.out.println("📌 Categorías iniciales insertadas correctamente.");
        } else {
            System.out.println("✅ Categorías ya existen en la base de datos.");
        }
    }
}

