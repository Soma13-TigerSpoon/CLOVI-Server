package com.clovi.repository.Category;

import com.clovi.domain.category.Category;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
  Optional<Category> findByIdAndIsDeletedIsFalse(Long id);
}
