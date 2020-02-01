package com.example.countrieslorenraz;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countrieslorenraz.jsonParser.CountriesJasonParse;

import java.util.ArrayList;

public class CountriesListViewModel extends ViewModel {

    private MutableLiveData<ArrayList<CountriesJasonParse>> _countriesList;
    private MutableLiveData<Integer> _area;
    private CountriesRepository _repo;

     void init(){
        _repo = CountriesRepository.getInstance();
    }

    public LiveData<ArrayList<CountriesJasonParse>> getCountriesList(){
        return _repo.getCountries();
    }

    public ArrayList<CountriesJasonParse> getCountriesListByName(){
        return _repo.getCountriesListByName();
    }

    public ArrayList<CountriesJasonParse> getCountriesListByArea(){
        return _repo.getCountriesListByArea();
    }
}
