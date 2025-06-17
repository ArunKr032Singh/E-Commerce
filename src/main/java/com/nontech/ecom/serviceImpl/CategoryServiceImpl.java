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
import com.nontech.ecom.payload.CategoryDTO;
import com.nontech.ecom.payload.CategoryResponse;
import com.nontech.ecom.repository.CategoryRepo;
import com.nontech.ecom.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize,sortByAndOrder);
        Page<Category> categoryPage = categoryRepo.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();
        if (categories.isEmpty()) {
            throw new APIException("No Category created till now !!");
        }
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategoryDb = categoryRepo.findByCategoryName(categoryDTO.getCategoryName());
        if (savedCategoryDb != null) {
            throw new APIException("Category with the name" + category.getCategoryName() + " already exist !!");
        }
        Category savedCategory = categoryRepo.save(category);
        CategoryDTO savedCategoryDto = modelMapper.map(savedCategory, CategoryDTO.class);
        return savedCategoryDto;
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category savedCategory = categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepo.delete(savedCategory);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Category savedCategory = categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "categoryId", categoryId));

        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setCategoryId(savedCategory.getCategoryId());
        savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
}
