package com.jahircelorio.consumo_api2.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface object {
    @GET("https://jsonplaceholder.typicode.com/comments/{id}")
    public Call<object> findCall(@Path("id") String id);
}
