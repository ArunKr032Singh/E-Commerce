/**
 * Created By Arun Singh
 * Date:13-06-2025
 * Time:14:02
 * Project Name:E-Commerce
 */

package com.nontech.ecom.service;

import com.nontech.ecom.model.Category;
import com.nontech.ecom.payload.CategoryDTO;
import com.nontech.ecom.payload.CategoryResponse;


public interface CategoryService {

    CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize,String sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);

}
