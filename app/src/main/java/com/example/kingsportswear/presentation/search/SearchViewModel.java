package com.example.kingsportswear.presentation.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kingsportswear.data.model.Product;
import com.example.kingsportswear.domain.repository.ProductRepository;
import com.example.kingsportswear.utils.ResultSetCallback;

import java.util.List;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {
    @Inject
    ProductRepository repository;

    private MutableLiveData<List<Product>> _products = new MutableLiveData<>();
    public LiveData<List<Product>> products = _products;

    private MutableLiveData<SearchActionState> _actionState = new MutableLiveData<>();
    public LiveData<SearchActionState> actionState = _actionState;

    @Inject
    public SearchViewModel(ProductRepository repository) {
        this.repository = repository;
    }

    public void getProducts(String searchKey, String category) {
        _actionState.postValue(SearchActionState.loading);
        repository.getProducts(searchKey, category, new ResultSetCallback<List<Product>>() {
            @Override
            public void onSuccess(List<Product> result) {
                _products.postValue(result);
                _actionState.postValue(SearchActionState.success);
            }

            @Override
            public void onError(Exception e) {
                _actionState.postValue(SearchActionState.error);
            }
        });
    }

    public void resetState() {
        _actionState.postValue(SearchActionState.idle);
        _products.postValue(null);
    }
}
