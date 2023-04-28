package com.clovi.app.category.repository;

import com.clovi.app.category.domain.Category;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
  Optional<Category> findByIdAndDeletedIsFalse(Long id);
}
