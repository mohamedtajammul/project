package com.example.productcatalogservice.clients;


import com.example.productcatalogservice.dto.FakeStoreDto;
import com.example.productcatalogservice.dto.ProductDto;
import com.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeStoreApiClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreDto getProductById(Long id) {
        ResponseEntity<FakeStoreDto> fakeStoreProductDtoResponseEntity =
                    requestForEntity("http://fakestoreapi.com/products/{id}", FakeStoreDto.class, HttpMethod.GET, id);
        return fakeStoreProductDtoResponseEntity.getBody();
    }

    public FakeStoreDto[] getAllProducts() {
        ResponseEntity<FakeStoreDto[]> fakeStoreAllProductsDtoResponseEntity =
                requestForEntity("http://fakestoreapi.com/products", FakeStoreDto[].class, HttpMethod.GET);
        return fakeStoreAllProductsDtoResponseEntity.getBody();
    }


    private <T> ResponseEntity<T> requestForEntity(String url, Class<T> responseType, HttpMethod requestMethod, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, requestMethod, requestCallback, responseExtractor, uriVariables);
    }
}
