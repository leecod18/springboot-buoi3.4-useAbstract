package com.example.demo.service.impl;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.enity.Category;
import com.example.demo.enity.Product;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.AbstractCrudService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DefaulCategoryService
        extends AbstractCrudService<CategoryRequest,CategoryRepository,Category ,Long, CategoryResponse >
        implements CategoryService {

    private final ProductService productService;

    public DefaulCategoryService(CategoryRepository repository, ProductService productService) {
        super(repository);
        this.productService = productService;
    }
    @Override
    public Category CreateAndSave(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        Category saveCategory = repository.save(category);
        return saveCategory;
    }

    @Override
    public List<ProductResponse> findProductsByCategoryID(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new ResourceNotFound(id + "Id not found"));
        List<ProductResponse> productRes = new ArrayList<>();
        List<Product> products = category.getProducts();
        for (Product product : products) {
            ProductResponse productResponse = productService.toResponse(product);
            productRes.add(productResponse);
        }
        return productRes;
        /*
         cach 2
                List<Product> products = category.getProducts();
                List<ProductResponse> res = products.stream().map(p -> {
                    return productService.toResponse(p);
                }).toList();
            return res;
        */

    }
    @Override
    protected Category SaveAndUpdate(Long id, CategoryRequest request) {
        Category category = repository.findById(id).orElseThrow(() -> new ResourceNotFound(id + "Id not found"));
        category.setName(request.getName());
        Category saveCategory = repository.save(category);
        return saveCategory;
    }
    @Override
    public CategoryResponse toResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }
}