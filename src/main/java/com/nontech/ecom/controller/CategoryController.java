/**
 * Created By Arun Singh
 * Date:13-06-2025
 * Time:13:18
 * Project Name:E-Commerce
 */

package com.nontech.ecom.controller;

import com.nontech.ecom.config.AppConstants;
import com.nontech.ecom.model.Category;
import com.nontech.ecom.payload.CategoryDTO;
import com.nontech.ecom.payload.CategoryResponse;
import com.nontech.ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                             @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                             @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY,required = false) String sortBy,
                                                             @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIRECTION,required = false) String sortOrder) {
        CategoryResponse categoryResponse = categoryService.getAllCategory(pageNumber, pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
        CategoryDTO deletedCategoryDTO = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(deletedCategoryDTO, HttpStatus.OK);
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId) {
        CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }
}
