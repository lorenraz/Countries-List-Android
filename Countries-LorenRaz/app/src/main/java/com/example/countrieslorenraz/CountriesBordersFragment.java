package com.example.countrieslorenraz;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.countrieslorenraz.jsonParser.CountriesJasonParse;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountriesBordersFragment extends Fragment implements CountriesListAdapter.onItemClickListener {

    private RecyclerView _recyclerView;
    private CountriesListAdapter _adapter;
    private CountriesBordersViewModel _viewModel;

    private ArrayList<String> _bordersCca3List;
    private ArrayList<String> _bordersNameList;
    private ArrayList<CountriesJasonParse> _bordersObjectList;
    private TextView _text;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_countries_list, container, false);

        _text = v.findViewById(R.id.fragText);
        _recyclerView = v.findViewById(R.id.countriesRecycler);

        _viewModel = new ViewModelProvider(this).get(CountriesBordersViewModel.class);
        _viewModel.init();

        _bordersCca3List = getArguments().getStringArrayList("borders");

        if(_bordersCca3List.size() > 0){
            _bordersObjectList = _viewModel.getBorders(_bordersCca3List);
            initRecyclerView();
        }
        else{
            _text.setText("this country has no borders");
        }
        return v;
    }

    private void initRecyclerView(){

        _recyclerView.setHasFixedSize(true);
        _recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        _adapter = new CountriesListAdapter(_bordersObjectList, this);
        _recyclerView.setAdapter(_adapter);
    }

    @Override
    public void onItemClick(CountriesJasonParse item) {

    }
}
