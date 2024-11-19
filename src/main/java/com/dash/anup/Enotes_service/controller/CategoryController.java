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
import org.springframework.util.ObjectUtils;
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

    @GetMapping("/")
    public ResponseEntity<?> getAllCategory(){
        List<CategoryResponse> allCategories = categoryService.getActiveCategory();
        if (CollectionUtils.isEmpty(allCategories)){
            return ResponseEntity.noContent().build();
        }else {
            return new ResponseEntity<>(allCategories, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id){
        CategoryResponse categoryById = categoryService.getCategoryById(id);
        if (ObjectUtils.isEmpty(categoryById)){
            return new ResponseEntity<>("Category not found with id="+id,HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(categoryById,HttpStatus.OK);
        }
    }

    @GetMapping("/trash")
    public ResponseEntity<?> getInActiveCategory(){
        List<CategoryResponse> inActiveCategory = categoryService.getInActiveCategory();
        if (CollectionUtils.isEmpty(inActiveCategory)){
            return ResponseEntity.noContent().build();
        }else {
            return new ResponseEntity<>(inActiveCategory, HttpStatus.OK);
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
        Boolean deleted = categoryService.deleteCategory(id);
        if (deleted){
            return new ResponseEntity<>("Category deleted successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/permanent-delete/{id}")
    public ResponseEntity<?> permanentDeleteCategory(@PathVariable Integer id){
        Boolean deleted = categoryService.permanentDeleteCategory(id);
        if (deleted){
            return new ResponseEntity<>("Category deleted successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
