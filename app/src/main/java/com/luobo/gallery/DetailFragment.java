package com.luobo.gallery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.util.concurrent.ExecutionException;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;


public class DetailFragment extends Fragment {
    private static int REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ViewPager2 viewPager = view.findViewById(R.id.viewPager);
        PhotoDetailListAdapter adapter = new PhotoDetailListAdapter(getContext(), new PhotoDiffUtil());
        PhotoViewModel viewModel = new ViewModelProvider(requireActivity()).get(PhotoViewModel.class);
        adapter.submitList(viewModel.getPhotos().getValue());
        viewPager.setAdapter(adapter);

        //Go to click item photo before
        Bundle bundle = getArguments();
        if (bundle != null) {
            viewPager.setCurrentItem(MainFragmentArgs.fromBundle(getArguments()).getCurrentPhoto());
        }

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        adapter.setOnItemClickListener((item, position) -> {
            requestPermission();
            FutureTarget<Bitmap> futureTarget = Glide.with(this)
                    .asBitmap()
                    .load(adapter.getCurrentList().get(position).getLargeImageURL())
                    .submit();
            new Thread(() -> {
                try {
                    Bitmap myBitmap = futureTarget.get();
                    new SaveImage(requireContext()).saveImages("dd", myBitmap);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
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
            ActivityCompat.requestPermissions(requireActivity(), permissions, REQUEST_WRITE_EXTERNAL_STORAGE);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(getContext(), "未给予权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

}