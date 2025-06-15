/**
 * Created By Arun Singh
 * Date:15-06-2025
 * Time:13:57
 * Project Name:E-Commerce
 */

package com.nontech.ecom.exceptions;

public class APIException extends RuntimeException{
    private static final Long serialVersionUID = 1L;

    public APIException() {
    }

    public APIException(String message) {
        super(message);
    }
}
