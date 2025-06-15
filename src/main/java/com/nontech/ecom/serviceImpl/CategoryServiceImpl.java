/**
 * Created By Arun Singh
 * Date:13-06-2025
 * Time:14:06
 * Project Name:E-Commerce
 */

package com.nontech.ecom.serviceImpl;

import com.nontech.ecom.exceptions.APIException;
import com.nontech.ecom.exceptions.ResourceNotFoundException;
import com.nontech.ecom.model.Category;
import com.nontech.ecom.repository.CategoryRepo;
import com.nontech.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategory() {

        List<Category> categories = categoryRepo.findAll();
        if(categories.isEmpty()){
            throw new APIException("No Category created till now !!");
        }
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepo.findByCategoryName(category.getCategoryName());
        if (savedCategory != null) {
            throw new APIException("Category with the name" + category.getCategoryName() + " already exist !!");
        }
        categoryRepo.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category savedCategory = categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepo.delete(savedCategory);
        return "Category with categoryId: " + categoryId + " deleted successfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category savedCategory = categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "categoryId", categoryId));

        category.setCategoryId(savedCategory.getCategoryId());
        savedCategory = categoryRepo.save(category);
        return savedCategory;
    }
}
