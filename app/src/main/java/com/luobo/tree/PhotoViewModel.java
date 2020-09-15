package com.luobo.tree;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.luobo.tree.repository.Photo;
import com.luobo.tree.repository.PhotoRepository;

import java.util.List;

public class PhotoViewModel extends AndroidViewModel {
    PhotoRepository repository;
    private MutableLiveData<List<Photo.HitsBean>> photos = new MutableLiveData<>();

    public PhotoViewModel(@NonNull Application application) {
        super(application);
        repository = new PhotoRepository();
    }

    public MutableLiveData<List<Photo.HitsBean>> getPhotoLiveData(String keywords) {
        photos = repository.getPhotoLiveData(keywords);
        return photos;
    }

    public MutableLiveData<List<Photo.HitsBean>> getPhotos() {
        return photos;
    }

    public void cleanOldData() {
        repository.cleanOldData();
    }

}
