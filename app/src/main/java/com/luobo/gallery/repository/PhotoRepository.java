package com.luobo.gallery.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PhotoRepository {
    private PhotoDataSource dataSource = new PhotoDataSource();

    public MutableLiveData<List<Photo.HitsBean>> getPhotoLiveData(String keywords) {
        return dataSource.getPhotosByNet(keywords);
    }

    public void cleanOldData() {
        dataSource.data.setValue(null);
    }
}
