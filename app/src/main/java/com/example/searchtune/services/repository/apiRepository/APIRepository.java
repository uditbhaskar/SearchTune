package com.example.searchtune.services.repository.apiRepository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.searchtune.services.model.Results;
import com.example.searchtune.services.model.Root;
import com.example.searchtune.services.repository.apiInterface.API;
import com.example.searchtune.services.repository.roomDatabase.RootDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRepository {

    private final API api;
    public MutableLiveData<Root> rootLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> success = new MutableLiveData<>();

    public APIRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         api = retrofit.create(API.class);
    }

    public MutableLiveData<Root> loadTunesInfo(String search, Context context) {

        api.getTunesInfo(search).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                assert response.body() != null;
                insertInDatabase(search, context, response.body().getResults());
                rootLiveData.setValue(response.body());
                success.setValue(true);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                rootLiveData.setValue(null);
                success.setValue(false);
            }
        });
        return rootLiveData;
    }

    private void insertInDatabase(String search, Context context, List<Results> results) {
        RootDatabase rootDatabase = RootDatabase.getInstance(context);
        Root root = new Root(results, search.trim().toLowerCase());
        rootDatabase.tuneRootDao().insertRoot(root);
    }

}
