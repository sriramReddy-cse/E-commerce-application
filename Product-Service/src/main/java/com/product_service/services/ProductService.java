package com.product_service.services;

import com.product_service.dto.ProductRequest;
import com.product_service.dto.ProductResponse;
import com.product_service.models.Product;
import com.product_service.repos.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo productRepo;

    public ProductResponse saveProduct(ProductRequest productRequest){
        //control+alt+v
        Product product = Product.builder()
                     .name(productRequest.name())
                     .price(productRequest.price())
                     .description(productRequest.description())
                     .build();
        Product savedProduct = productRepo.save(product);
        log.info("saved Product created successfully");
             return new ProductResponse(savedProduct.getId(),savedProduct.getName(),savedProduct.getDescription(),
                     savedProduct.getPrice());
    }

    public List<ProductResponse> getALlProducts() {
        return productRepo.findAll()
                .stream()
                .map(product->
                        new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }

}
