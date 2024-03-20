package com.example.kingsportswear.domain.repository;

import com.example.kingsportswear.data.model.Product;
import com.example.kingsportswear.utils.ResultSetCallback;

import java.util.List;

public interface ProductRepository {
    void getProducts(String searchKey, String category, ResultSetCallback<List<Product>> callback);
}
