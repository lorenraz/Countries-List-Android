package com.example.countrieslorenraz.jsonParser;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CountriesJasonParse {

    @SerializedName("name")
    private CountriesNameJasonParser _name;

    @SerializedName("area")
    private double _area;

    @SerializedName("cca3")
    private String _cca3;

    @SerializedName("borders")
    private ArrayList<String> _borders;

    public CountriesNameJasonParser get_name() {
        return _name;
    }

    public double get_area() {
        return _area;
    }

    public ArrayList<String> get_borders(){
        return _borders;
    }

    public String get_cca3(){
        return _cca3;
    }
}
