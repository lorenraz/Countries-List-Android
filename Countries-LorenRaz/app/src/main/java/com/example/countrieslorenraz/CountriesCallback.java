package com.example.countrieslorenraz;

public interface CountriesCallback<T> {

    void onSuccess(T result);

    void onFailure(Throwable t);
}
