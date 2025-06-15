/**
 * Created By Arun Singh
 * Date:15-06-2025
 * Time:01:14
 * Project Name:E-Commerce
 */

package com.nontech.ecom.repository;

import com.nontech.ecom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByCategoryName(String categoryName);
}
