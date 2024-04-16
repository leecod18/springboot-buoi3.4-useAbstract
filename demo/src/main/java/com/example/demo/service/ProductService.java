package com.example.demo.service;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.enity.Product;

public interface ProductService extends BaseCrudService<ProductRequest, Long, ProductResponse>{
     ProductResponse toResponse(Product saveProduct);
}
