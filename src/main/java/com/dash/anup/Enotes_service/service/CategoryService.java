package com.dash.anup.Enotes_service.service;

import com.dash.anup.Enotes_service.dto.CategoryDto;
import com.dash.anup.Enotes_service.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    Boolean saveCategory(CategoryDto categoryDto);
    List<CategoryResponse> getActiveCategory();
    List<CategoryResponse> getInActiveCategory();
    CategoryResponse getCategoryById(Integer id);

    Boolean deleteCategory(Integer id);

    Boolean permanentDeleteCategory(Integer id);
}
