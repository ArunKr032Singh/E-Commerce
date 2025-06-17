/**
 * Created By Arun Singh
 * Date:17-06-2025
 * Time:09:34
 * Project Name:E-Commerce
 */

package com.nontech.ecom.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private List<ProductDTO> content;
}
