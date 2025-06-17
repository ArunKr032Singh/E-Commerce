/**
 * Created By Arun Singh
 * Date:15-06-2025
 * Time:19:24
 * Project Name:E-Commerce
 */

package com.nontech.ecom.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
}
