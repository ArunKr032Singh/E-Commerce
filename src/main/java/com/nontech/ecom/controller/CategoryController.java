/**
 * Created By Arun Singh
 * Date:13-06-2025
 * Time:13:18
 * Project Name:E-Commerce
 */

package com.nontech.ecom.controller;

import com.nontech.ecom.model.Category;
import com.nontech.ecom.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully", HttpStatus.OK);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try {
            String status = categoryService.deleteCategory(categoryId);
//            return new ResponseEntity<>(status, HttpStatus.OK);
//            return ResponseEntity.ok(status);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId){
        try{
            Category savedCategory = categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>("Category with categoryId: "+categoryId, HttpStatus.OK);
        }catch(ResponseStatusException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }
}
