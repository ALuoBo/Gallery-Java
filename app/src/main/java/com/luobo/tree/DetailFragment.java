package com.luobo.tree;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.util.concurrent.ExecutionException;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;


public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_detail);
        PhotoDetailListAdapter adapter = new PhotoDetailListAdapter(getContext(), new PhotoDiffUtil());
        PhotoViewModel viewModel = new ViewModelProvider(requireActivity()).get(PhotoViewModel.class);
        adapter.submitList(viewModel.getPhotos().getValue().getHits());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.setOnItemClickListener((item, position) -> {
            requestPermission();
            FutureTarget<Bitmap> futureTarget = Glide.with(this)
                    .asBitmap()
                    .load(adapter.getCurrentList().get(position).getLargeImageURL())
                    .submit();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap myBitmap = futureTarget.get();
                        new SaveImage(requireContext()).saveImages("dd", myBitmap);
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        });
        // Inflate the layout for this fragment
        return view;
    }

    private void requestPermission() {
        if (!haveStoragePermission()) {
            String[] permissions = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            ActivityCompat.requestPermissions(requireActivity(), permissions, 1);
        }

    }

    private boolean haveStoragePermission() {
        return PERMISSION_GRANTED == ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View decorView = getActivity().getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

}