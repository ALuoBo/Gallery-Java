package com.luobo.tree.repository;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface PixabayService {
    @GET("api")
    Call<Photo> listPhotos(@QueryMap Map<String, String> parameter);
}
