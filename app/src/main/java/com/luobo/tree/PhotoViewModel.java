package com.luobo.tree;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.luobo.tree.repository.Photo;
import com.luobo.tree.repository.PhotoRepository;

public class PhotoViewModel extends AndroidViewModel {
    PhotoRepository repository;
    private MutableLiveData<Photo> photos =new MutableLiveData<>();

    public PhotoViewModel(@NonNull Application application) {
        super(application);
        repository = new PhotoRepository();
    }

    public MutableLiveData<Photo> getPhotoLiveData(String keywords) {
        photos = repository.getPhotoLiveData(keywords);
        return photos;
    }

    public MutableLiveData<Photo> getPhotos() {
        return photos;
    }
}
