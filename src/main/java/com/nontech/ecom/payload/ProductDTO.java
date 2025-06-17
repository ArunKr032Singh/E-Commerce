/**
 * Created By Arun Singh
 * Date:17-06-2025
 * Time:09:33
 * Project Name:E-Commerce
 */

package com.nontech.ecom.payload;

import com.nontech.ecom.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private String image;
    private String description;
    private Integer quantity;
    private double price;
    private double discount;
    private double specialPrice;
    private Category category;
}
