package com.dash.anup.Enotes_service.repository;

import com.dash.anup.Enotes_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByIsActiveTrue();

    List<Category> findByIsActiveFalse();

    Optional<Category> findByIdAndIsActiveTrue(Integer id);
}
