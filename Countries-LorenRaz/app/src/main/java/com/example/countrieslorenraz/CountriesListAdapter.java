package com.example.countrieslorenraz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrieslorenraz.jsonParser.CountriesJasonParse;

import java.util.ArrayList;
import java.util.List;

public class CountriesListAdapter  extends RecyclerView.Adapter<CountriesListAdapter.CountriesViewHolder>{

    private ArrayList<CountriesJasonParse> _countriesList;
    private onItemClickListener _listener;


    public CountriesListAdapter(ArrayList<CountriesJasonParse> countries, onItemClickListener listener){
        _countriesList = countries;
        _listener = listener;
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.countries_item_view, parent, false);
        return new CountriesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        final CountriesJasonParse currentItem = _countriesList.get(position);

        String countryEnglishName = currentItem.get_name().get_common();
        String countryCommonName = currentItem.get_name().get_native().get_common();
        String countryName = countryEnglishName + " " + countryCommonName;

        holder._countryName.setText(countryName);

        holder._countryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _listener.onItemClick(currentItem);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return _countriesList.size();
    }

    public class CountriesViewHolder extends RecyclerView.ViewHolder{
        public TextView _countryName;

        public CountriesViewHolder(View itemView){
            super(itemView);
            _countryName = itemView.findViewById(R.id.countryName);
        }
    }

    public void setCountriesList( ArrayList<CountriesJasonParse> list){
        _countriesList = list;
    }

    public interface onItemClickListener{
        void onItemClick(CountriesJasonParse item);
    }
}
