package com.example.productcatalogservice.controller;

import com.example.productcatalogservice.dto.CategoryDto;
import com.example.productcatalogservice.dto.FakeStoreDto;
import com.example.productcatalogservice.dto.ProductDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RestController is Controller + ResponseBody
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        List<ProductDto> response = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for(Product product: products) {
            response.add(getProductDto(product));
        }
        return response;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") long productId) {
        //if the variable names are different in URI and parameter then use @PathVariable("var_name")
        try {
            if(productId <= 0) {
                throw new IllegalArgumentException("Invalid Product Id");
            }
            Product product = productService.getProductById(productId);
            ProductDto productDto = getProductDto(product);
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }catch (IllegalArgumentException exception) {
            throw exception;
        }

    }

    @PutMapping("/product/{id}")
    public ProductDto replaceProduct(@PathVariable long id, @RequestBody ProductDto productDto) {

        return productDto;
    }

    @PostMapping("/products")
    public ProductDto addProduct(@RequestBody ProductDto product) {
        return product;
    }

    private ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        if(product.getCategory() != null) {
            CategoryDto category = new CategoryDto();
            category.setId(product.getCategory().getId());
            category.setName(product.getCategory().getName());
            productDto.setCategory(category);
        }
        return productDto;
    }
}
