package com.example.countrieslorenraz;

import com.example.countrieslorenraz.jsonParser.CountriesJasonParse;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CountriesHttpClient {

    private OkHttpClient _client;
    private final String _baseUrl;
    private final Request.Builder _requestBuilde;

    public CountriesHttpClient(OkHttpClient client, String baseUrl){
        _client = client;
        _baseUrl = baseUrl;
        _requestBuilde = new Request.Builder();
    }

    public void getCountriesListByName(final CountriesCallback<ArrayList<CountriesJasonParse>> callback) {
        Request request = _requestBuilde.url(_baseUrl)
                .build();

        execute(request, CountriesJasonParse[].class, new CountriesCallback<CountriesJasonParse[]>() {
            @Override
            public void onSuccess(CountriesJasonParse[] result) {
                callback.onSuccess(new ArrayList<>(Arrays.asList(result)));
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    private <T> void execute(Request request, final Class<T> type, final CountriesCallback<T> callback)
    {
        _client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if(response.isSuccessful()) {
                    String meRes = response.body().string();
                    Gson gson = new Gson();
                    T parsedContent = gson.fromJson(meRes, type);
                    callback.onSuccess(parsedContent);

                }
                else {
                    callback.onFailure(new CountriesError(response.message()));
                }
            }
        });
    }
}
