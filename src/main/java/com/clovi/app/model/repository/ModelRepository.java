package com.clovi.app.model.repository;

import com.clovi.app.model.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
