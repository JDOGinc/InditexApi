package com.jdog.apirest.domain.repository;

import com.jdog.apirest.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findByName(String name);
}
