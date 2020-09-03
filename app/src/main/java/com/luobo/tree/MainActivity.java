package com.luobo.tree;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.tree.repository.Photo;

public class MainActivity extends AppCompatActivity {

    private PhotoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(PhotoViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        PhotoListAdapter adapter = new PhotoListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getPhotoLiveData("").observe(this, new Observer<Photo>() {
            @Override
            public void onChanged(Photo photo) {
                adapter.setPhotos(photo);
            }
        });
    }
}