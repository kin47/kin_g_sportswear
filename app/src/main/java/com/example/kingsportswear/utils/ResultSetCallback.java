package com.example.kingsportswear.utils;

public interface ResultSetCallback<T> {
    void onSuccess(T result);
    void onError(Exception e);
}
