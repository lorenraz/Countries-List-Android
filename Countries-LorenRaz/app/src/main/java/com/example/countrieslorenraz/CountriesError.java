package com.example.countrieslorenraz;

import androidx.annotation.Nullable;

class CountriesError extends Throwable {

    public CountriesError() {
    }

    public CountriesError(@Nullable String message) {
        super(message);
    }
}
