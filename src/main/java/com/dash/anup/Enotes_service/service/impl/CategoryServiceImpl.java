package com.dash.anup.Enotes_service.service.impl;

import com.dash.anup.Enotes_service.dto.CategoryDto;
import com.dash.anup.Enotes_service.dto.CategoryResponse;
import com.dash.anup.Enotes_service.entity.Category;
import com.dash.anup.Enotes_service.repository.CategoryRepository;
import com.dash.anup.Enotes_service.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //mapper helps in mapping objects from one model to another, reducing the need for manual mapping code.
    @Autowired
    private ModelMapper mapper;
    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {
        try {
            Category category = mapper.map(categoryDto,Category.class);
            category.setIsDeleted(false);
            category.setCreatedBy(1);
            category.setCreatedOn(new Date());
            Category saveCategory = categoryRepository.save(category);
            if (!ObjectUtils.isEmpty(saveCategory)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categories.stream().map(categoryDto -> mapper.map(categoryDto, CategoryDto.class)).toList();
        return categoryDtoList;
    }

    @Override
    public List<CategoryResponse> getActiveCategory() {
        List<Category> categories = categoryRepository.findByIsActiveTrue();
        List<CategoryResponse> activeCategories = categories.stream().map(category -> mapper.map(category, CategoryResponse.class)).toList();
        return activeCategories;
    }
}
