package com.example.kingsportswear.data.repository;

import com.example.kingsportswear.data.database.ProductDAO;
import com.example.kingsportswear.data.model.Product;
import com.example.kingsportswear.domain.repository.ProductRepository;
import com.example.kingsportswear.utils.ResultSetCallback;

import java.util.List;

import javax.inject.Inject;

public class ProductRepositoryImpl implements ProductRepository {
    private final ProductDAO service;

    @Inject
    public ProductRepositoryImpl(ProductDAO service) {
        this.service = service;
    }

    @Override
    public void getProducts(String searchKey, String category, ResultSetCallback<List<Product>> callback) {
        service.getProducts(searchKey, category, callback);
    }
}
