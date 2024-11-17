package com.dash.anup.Enotes_service.controller;

import com.dash.anup.Enotes_service.dto.CategoryDto;
import com.dash.anup.Enotes_service.dto.CategoryResponse;
import com.dash.anup.Enotes_service.entity.Category;
import com.dash.anup.Enotes_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto) {
        Boolean saveCategory = categoryService.saveCategory(categoryDto);
        if (saveCategory) {
            return new ResponseEntity<>("saved", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategory(){
        List<CategoryDto> allCategory = categoryService.getAllCategory();
        if (CollectionUtils.isEmpty(allCategory)){
            return ResponseEntity.noContent().build();
        }else {
            return new ResponseEntity<>(allCategory, HttpStatus.OK);
        }
    }

    @GetMapping("/active-categories")
    public ResponseEntity<?> getActiveCategory(){
        List<CategoryResponse> activeCategories = categoryService.getActiveCategory();
        if (CollectionUtils.isEmpty(activeCategories)){
            return ResponseEntity.noContent().build();
        }else {
            return new ResponseEntity<>(activeCategories, HttpStatus.OK);
        }
    }
}
