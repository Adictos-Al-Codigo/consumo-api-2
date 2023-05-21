package com.jahircelorio.consumo_api2.interfaces;

import com.jahircelorio.consumo_api2.models.Comentario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ComentarioApi {
    @GET("https://jsonplaceholder.typicode.com/comments/{id}")
    public Call<Comentario> find(@Path("id") String id);
}
