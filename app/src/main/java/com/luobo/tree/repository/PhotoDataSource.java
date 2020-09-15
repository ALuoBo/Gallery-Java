package com.luobo.tree.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;

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

    private boolean isLoading = false;
    private int perPage = 15;

    private int totalPage, currentPage = 1;
    MutableLiveData<List<Photo.HitsBean>> data = new MutableLiveData<>();
    HashMap<String, String> map = new HashMap<>();


    //todo :will quick request again & again until all data pull down. It's a serious issue .
    public MutableLiveData<List<Photo.HitsBean>> getPhotosByNet(String keywords) {

        if (isLoading) return data;
        isLoading = true;
        map.put("key", "18138703-f8467438c77adc35dabe81fca");
        map.put("q", keywords);
        map.put("image_type", "All");
        map.put("page", String.valueOf(currentPage));
        map.put("per_page", String.valueOf(perPage));
        service.listPhotos(map).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {

                if (data.getValue()==null) {
                    data.setValue(response.body().getHits());
                } else {
                    data.getValue().addAll(response.body().getHits());
                }
                totalPage = response.body().getTotalHits() / perPage;
                Log.e("MyTag", "onResponse: first request-----" + totalPage);
                isLoading = false;
            }
            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                Log.e("MyTag", "requestFailed", t);
                isLoading = false;
            }
        });
        currentPage++;
        return data;
    }
}