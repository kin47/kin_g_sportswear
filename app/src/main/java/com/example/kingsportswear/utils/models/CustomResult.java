package com.example.kingsportswear.utils.models;

public class CustomResult<T> {
    private final T result;
    private final Exception exception;

    public CustomResult(T result, Exception exception) {
        this.result = result;
        this.exception = exception;
    }

    public T getData() {
        return result;
    }

    public Exception getException() {
        return exception;
    }

    public boolean isSuccess() {
        return exception == null;
    }
}
