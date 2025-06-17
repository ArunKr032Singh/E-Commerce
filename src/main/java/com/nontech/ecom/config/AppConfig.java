/**
 * Created By Arun Singh
 * Date:15-06-2025
 * Time:20:42
 * Project Name:E-Commerce
 */

package com.nontech.ecom.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
