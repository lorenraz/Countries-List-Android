package com.example.countrieslorenraz.jsonParser;

import com.google.gson.annotations.SerializedName;

public class CountriesNameJasonParser {

    @SerializedName("common")
    private String _common;

    @SerializedName("native")
    private CountriesOriginalNameJsonParser _native;

    public String get_common() {
        return _common;
    }

    public CountriesOriginalNameJsonParser get_native() {
        return _native;
    }
}
