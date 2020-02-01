package com.example.countrieslorenraz;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.countrieslorenraz.jsonParser.CountriesJasonParse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class CountriesRepository {

    private static CountriesRepository _instance;

    private ArrayList<CountriesJasonParse> _countriesList = new ArrayList<>();
    private HashMap<String, CountriesJasonParse> _cca3ToCountryMap;

    private CountriesHttpClient _countriesHttpClient;

    public static CountriesRepository getInstance(){
        if(_instance == null){
            _instance = new CountriesRepository();
        }
        return _instance;
    }

    public void setHtttpClient(CountriesHttpClient client)
    {
        _countriesHttpClient = client;
    }

    public LiveData<ArrayList<CountriesJasonParse>> getCountries(){

        final MutableLiveData<ArrayList<CountriesJasonParse>> data = new MutableLiveData<>();
        _countriesHttpClient.getCountriesListByName(new CountriesCallback<ArrayList<CountriesJasonParse>>() {
            @Override
            public void onSuccess(ArrayList<CountriesJasonParse> result) {
                _countriesList = result;
                data.postValue(result);
                setHashData(result);
            }

            @Override
            public void onFailure(Throwable t) {
                //handle error
            }
        });
        return data;
    }

    public ArrayList<CountriesJasonParse> getCountriesListByArea(){
        Collections.sort(_countriesList, new Comparator<CountriesJasonParse>() {
            @Override
            public int compare(CountriesJasonParse o1, CountriesJasonParse o2) {
                return (int) (o2.get_area() - o1.get_area());
            }
        });
        return _countriesList;
    }

    public ArrayList<CountriesJasonParse> getCountriesListByName(){
        Collections.sort(_countriesList, new Comparator<CountriesJasonParse>() {
            @Override
            public int compare(CountriesJasonParse o1, CountriesJasonParse o2) {
                return (o1.get_name().get_common().compareTo(o2.get_name().get_common()));
            }
        });
        return _countriesList;
    }

    public ArrayList<CountriesJasonParse> getBordersList(ArrayList<String> cca3List){
        ArrayList<CountriesJasonParse> res = new ArrayList<>();
        for(int i = 0; i < cca3List.size(); i++){
            res.add(_cca3ToCountryMap.get(cca3List.get(i)));
        }
        return res;
    }

    //creates hashMap of Key = cca3, Value = CountriesJasonParse
    private void setHashData(ArrayList<CountriesJasonParse> data){
        _cca3ToCountryMap = new HashMap<>();
        for(int i = 0; i < data.size(); i++){
            _cca3ToCountryMap.put(data.get(i).get_cca3(), data.get(i));
        }
    }
}
