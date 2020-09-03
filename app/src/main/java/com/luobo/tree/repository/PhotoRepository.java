package com.luobo.tree.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PhotoRepository {
    private PhotoDataSource dataSource = new PhotoDataSource();

    public MutableLiveData<Photo> getPhotoLiveData(String keywords) {
        return dataSource.getPhotosByNet(keywords);
    }
}
