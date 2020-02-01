package com.example.countrieslorenraz;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.countrieslorenraz.jsonParser.CountriesJasonParse;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountriesListFragment extends Fragment implements CountriesListAdapter.onItemClickListener {

    private RecyclerView _recyclerView;
    private CountriesListAdapter _adapter;
    private CountriesListViewModel _viewModel;

    private Button _byNameButton;
    private Button _byAreaButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_countries_list, container, false);
        _byAreaButton = v.findViewById(R.id.setByArea);
        _byAreaButton.setVisibility(View.GONE);
        _byNameButton = v.findViewById(R.id.setByName);
        _byNameButton.setVisibility(View.GONE);
        _byNameButton.setSelected(true);

        _byNameButton.setOnClickListener(v1 -> {
            _adapter.setCountriesList(_viewModel.getCountriesListByName());
            _byNameButton.setSelected(true);
            _byAreaButton.setSelected(false);
            _adapter.notifyDataSetChanged();
            _recyclerView.getLayoutManager().scrollToPosition(0);
        });


        _byAreaButton.setOnClickListener(v12 -> {
            _recyclerView.getLayoutManager().scrollToPosition(0);
            _adapter.setCountriesList(_viewModel.getCountriesListByArea());
            _byNameButton.setSelected(false);
            _byAreaButton.setSelected(true);
            _adapter.notifyDataSetChanged();
        });
        _recyclerView = v.findViewById(R.id.countriesRecycler);
        initRecyclerView();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _viewModel = new ViewModelProvider(this).get(CountriesListViewModel.class);
        _viewModel.init();
        _viewModel.getCountriesList().observe(getViewLifecycleOwner(), this::onCountriesListReceived);
    }


    private void initRecyclerView() {
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

    }

    private void onCountriesListReceived(ArrayList<CountriesJasonParse> countriesJasonParses) {
        _adapter = new CountriesListAdapter(countriesJasonParses, this);
        _recyclerView.setAdapter(_adapter);
        _byAreaButton.setVisibility(View.VISIBLE);
        _byNameButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(CountriesJasonParse item) {

        CountriesBordersFragment nextFrag = new CountriesBordersFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, nextFrag)
                .addToBackStack(null)
                .commit();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("borders", item.get_borders());
        nextFrag.setArguments(bundle);
    }
}
