/**
 * Created By Arun Singh
 * Date:16-06-2025
 * Time:12:37
 * Project Name:E-Commerce
 */

package com.nontech.ecom.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse {
    private String message;
    private boolean status;
}
