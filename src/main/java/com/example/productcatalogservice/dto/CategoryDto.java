package com.example.productcatalogservice.dto;

import com.example.productcatalogservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private long id;
    private String name;
    private String description;
}
