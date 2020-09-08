package com.luobo.tree.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class PhotoDataSource {
    Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    PixabayService service = retrofit.create(PixabayService.class);

    public MutableLiveData<Photo> getPhotosByNet(String keywords) {

        HashMap<String, String> map = new HashMap<>();
        map.put("key", "18138703-f8467438c77adc35dabe81fca");
        map.put("q", keywords);
        map.put("image_type", "All");
        map.put("per_page", "5");

        MutableLiveData<Photo> data = new MutableLiveData<>();
        service.listPhotos(map).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                data.setValue(response.body());
                Log.e("MyTag", "onResponse: " + "success" + data.getValue());
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }

        });
        return data;
    }

}
