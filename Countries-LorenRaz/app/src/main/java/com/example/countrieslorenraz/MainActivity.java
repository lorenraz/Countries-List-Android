package com.example.countrieslorenraz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.squareup.okhttp.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private CountriesRepository _repository;
    private final static String BASE_URL = "https://restcountries.herokuapp.com/api/v1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _repository = CountriesRepository.getInstance();
        OkHttpClient client = new OkHttpClient();
        CountriesHttpClient countriesHttpClient = new CountriesHttpClient(client, BASE_URL);
        _repository.setHtttpClient(countriesHttpClient);
        FragmentManager fragmentManager = getSupportFragmentManager();
        CountriesListFragment fragment = new CountriesListFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
