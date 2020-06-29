package com.example.vuanews.response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WebserviceApi {

    @GET
    Call<NewsResponse> getResponse(@Url String endUrl);

}
