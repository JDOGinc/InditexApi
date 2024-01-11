package com.jdog.apirest.infrastructure.persistence;

import com.jdog.apirest.domain.model.Category;
import com.jdog.apirest.domain.repository.CategoryRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoCategoryRepository extends MongoRepository<Category, String>, CategoryRepository {

}
