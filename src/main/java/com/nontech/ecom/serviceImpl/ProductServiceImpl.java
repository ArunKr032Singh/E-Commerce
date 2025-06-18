/**
 * Created By Arun Singh
 * Date:17-06-2025
 * Time:09:30
 * Project Name:E-Commerce
 */

package com.nontech.ecom.serviceImpl;

import com.nontech.ecom.exceptions.ResourceNotFoundException;
import com.nontech.ecom.model.Category;
import com.nontech.ecom.model.Product;
import com.nontech.ecom.payload.ProductDTO;
import com.nontech.ecom.payload.ProductResponse;
import com.nontech.ecom.repository.CategoryRepo;
import com.nontech.ecom.repository.ProductRepo;
import com.nontech.ecom.service.FileService;
import com.nontech.ecom.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO) {

        Product product = modelMapper.map(productDTO, Product.class);

        Category category = categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "categoryId", categoryId));

        product.setImage("default.png");
        product.setCategory(category);
        double specialPrice = productDTO.getPrice() - (productDTO.getDiscount() * 0.01) * productDTO.getPrice();
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepo.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> productList = productRepo.findAll();
        List<ProductDTO> productDTOS = productList.stream().map(product ->
                modelMapper.map(product, ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "categoryId", categoryId));

        List<Product> productList = productRepo.findByCategoryOrderByPriceAsc(category);
        List<ProductDTO> productDTOS = productList.stream().map(product ->
                modelMapper.map(product, ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword) {

        List<Product> productList = productRepo.findByProductNameLikeIgnoreCase("%" + keyword + "%");
        List<ProductDTO> productDTOS = productList.stream().map(product ->
                modelMapper.map(product, ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product productFromDB = productRepo.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "productId", productId));

        productFromDB.setProductName(productDTO.getProductName());
        productFromDB.setDescription(productDTO.getDescription());
        productFromDB.setQuantity(productDTO.getQuantity());
        productFromDB.setDiscount(productDTO.getDiscount());
        productFromDB.setPrice(productDTO.getPrice());

        double specialPrice = productDTO.getPrice() - (productDTO.getDiscount() * 0.01) * productDTO.getPrice();

        productFromDB.setSpecialPrice(specialPrice);

        Product savedProduct = productRepo.save(productFromDB);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {

        Product productFromDB = productRepo.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "productId", productId));

        productRepo.delete(productFromDB);

        return modelMapper.map(productFromDB, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        Product productFromDB = productRepo.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "productId", productId));
        String fileName = fileService.uploadImage(path, image);

        productFromDB.setImage(fileName);
        Product updatedProduct = productRepo.save(productFromDB);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }


}



















