/**
 * Created By Arun Singh
 * Date:13-06-2025
 * Time:14:02
 * Project Name:E-Commerce
 */

package com.nontech.ecom.service;

import com.nontech.ecom.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    void createCategory(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
