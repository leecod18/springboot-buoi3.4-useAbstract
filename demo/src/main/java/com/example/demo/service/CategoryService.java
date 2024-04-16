package com.example.demo.service;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.enity.Category;

import java.util.List;

public interface CategoryService extends BaseCrudService<CategoryRequest, Long, CategoryResponse> {
    List<ProductResponse> findProductsByCategoryID(Long id);

}
