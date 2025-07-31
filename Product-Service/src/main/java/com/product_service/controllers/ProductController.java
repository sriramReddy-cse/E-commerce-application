package com.product_service.controllers;


import com.product_service.dto.ProductRequest;
import com.product_service.dto.ProductResponse;
import com.product_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //create a product to the database
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody
                                         ProductRequest productRequest){
         return  productService.saveProduct(productRequest);
    }

    //method to get all the entries from the database product-service
    @GetMapping
    public List<ProductResponse>getAllProducts(){
         return productService.getALlProducts();
    }

}
