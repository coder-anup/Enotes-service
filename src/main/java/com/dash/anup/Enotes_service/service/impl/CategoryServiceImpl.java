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
import java.util.Optional;

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
    public List<CategoryResponse> getActiveCategory() {
        List<Category> categories = categoryRepository.findByIsActiveTrue();
        List<CategoryResponse> activeCategories = categories.stream().map(category -> mapper.map(category, CategoryResponse.class)).toList();
        return activeCategories;
    }

    @Override
    public List<CategoryResponse> getInActiveCategory() {
        List<Category> categories = categoryRepository.findByIsActiveFalse();
        List<CategoryResponse> allCategories = categories.stream().map(categoryDto -> mapper.map(categoryDto, CategoryResponse.class)).toList();
        return allCategories;
    }


    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Optional<Category> findCategoryById = categoryRepository.findByIdAndIsActiveTrue(id);
        if (findCategoryById.isPresent()){
            Category category = findCategoryById.get();
            return mapper.map(category, CategoryResponse.class);
        }
        return null;
    }

    @Override
    public Boolean deleteCategory(Integer id) {
        Optional<Category> findCategoryById = categoryRepository.findById(id);
        if (findCategoryById.isPresent()){
            Category category = findCategoryById.get();
            category.setIsActive(false);
            category.setIsDeleted(true);
            categoryRepository.save(category);
            return true;
        }
        return false;
    }

    @Override
    public Boolean permanentDeleteCategory(Integer id) {
        Optional<Category> findCategoryById = categoryRepository.findById(id);
        if (findCategoryById.isPresent()){
            Category category = findCategoryById.get();
            if (category.getIsDeleted()==true && category.getIsActive()==false){
                categoryRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }
}
