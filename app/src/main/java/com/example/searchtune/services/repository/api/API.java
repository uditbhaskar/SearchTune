package com.example.searchtune.services.repository.api;

import com.example.searchtune.services.model.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    String BASE_URL = "https://itunes.apple.com/";

    @GET("search")
    Call<Root> getTunesInfo(@Query(value = "term") String song);
}
