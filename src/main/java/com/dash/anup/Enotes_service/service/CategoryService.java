package com.dash.anup.Enotes_service.service;

import com.dash.anup.Enotes_service.dto.CategoryDto;
import com.dash.anup.Enotes_service.dto.CategoryResponse;
import com.dash.anup.Enotes_service.entity.Category;

import java.util.List;

public interface CategoryService {
    Boolean saveCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategory();
    List<CategoryResponse> getActiveCategory();
}
