package com.example.countrieslorenraz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countrieslorenraz.jsonParser.CountriesJasonParse;

import java.util.ArrayList;

public class CountriesBordersViewModel extends ViewModel {

    private MutableLiveData<ArrayList<CountriesJasonParse>> _countriesList;
    private CountriesRepository _repo;

    public void init(){
       _repo = CountriesRepository.getInstance();
    }

        public ArrayList<CountriesJasonParse> getBorders(ArrayList<String> bordersCode){
            return  _repo.getBordersList(bordersCode);
    }

}
