package com.example.productcatalogservice.services;

import com.example.productcatalogservice.clients.FakeStoreApiClient;
import com.example.productcatalogservice.dto.FakeStoreDto;
import com.example.productcatalogservice.dto.ProductDto;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements IProductService{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Override
    public Product getProductById(long id) {
        FakeStoreDto fakeStoreDto = fakeStoreApiClient.getProductById(id);
        if(fakeStoreDto != null) {
            return getProduct(fakeStoreDto);
        }
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreDto[] allProducts = fakeStoreApiClient.getAllProducts();
        List<Product> productsDto = new ArrayList<>();
        for(FakeStoreDto fakeStoreDto : allProducts) {
            productsDto.add(getProduct(fakeStoreDto));
        }
        return productsDto;
    }

    public Product replaceProduct(long id, Product product) {

        return product;
    }



    private Product getProduct(FakeStoreDto fakeStoreDto) {
        Product product = new Product();
        product.setId(fakeStoreDto.getId());
        product.setName(fakeStoreDto.getTitle());
        product.setPrice(fakeStoreDto.getPrice());
        product.setDescription(fakeStoreDto.getDescription());
        product.setImageUrl(fakeStoreDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreDto.getCategory());
        product.setCategory(category);
        return product;
    }


}
