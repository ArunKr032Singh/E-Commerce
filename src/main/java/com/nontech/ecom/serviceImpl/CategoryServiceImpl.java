/**
 * Created By Arun Singh
 * Date:13-06-2025
 * Time:14:06
 * Project Name:E-Commerce
 */

package com.nontech.ecom.serviceImpl;

import com.nontech.ecom.model.Category;
import com.nontech.ecom.repository.CategoryRepo;
import com.nontech.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categories = categoryRepo.findAll();
        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        categoryRepo.delete(category);
        return "Category with categoryId " + categoryId + " deleted successfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        List<Category> categories = categoryRepo.findAll();
        Optional<Category> optionalCategory = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            Category savedCategory = categoryRepo.save(existingCategory);
            return savedCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }
}
