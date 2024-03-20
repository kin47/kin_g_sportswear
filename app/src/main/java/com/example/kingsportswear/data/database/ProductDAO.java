package com.example.kingsportswear.data.database;

import android.util.Log;

import com.example.kingsportswear.data.model.Product;
import com.example.kingsportswear.utils.ResultSetCallback;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void getProducts(String searchKey, String category, ResultSetCallback<List<Product>> callback) {
        Query query = db.collection("product");
        if (category != null && !category.isEmpty()) {
            query = query.whereArrayContains("category_id", category);
        }
        List<Product> products = new ArrayList<>();
        query.get().addOnSuccessListener(queryDocumentSnapshots -> {
            if(searchKey != null && !searchKey.isEmpty()) {
                for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                    Product product = document.toObject(Product.class);
                    if (product.getName().toLowerCase().contains(searchKey.toLowerCase())) {
                        products.add(product);
                    }
                }
            } else {
                for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                    Product product = document.toObject(Product.class);
                    products.add(product);
                }
            }
            callback.onSuccess(products);
        }).addOnFailureListener(e -> callback.onError(e));
    }
}
