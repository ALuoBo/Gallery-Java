package com.luobo.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.luobo.gallery.repository.Photo;

import java.util.List;


public class MainFragment extends Fragment {
    private PhotoViewModel viewModel;
    private SearchView searchView;
    private String keyWord = "sea";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(PhotoViewModel.class);
        searchView = view.findViewById(R.id.searchView);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        PhotoListAdapter adapter = new PhotoListAdapter(getContext(), new PhotoDiffUtil());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));
        viewModel.getPhotoLiveData(keyWord).observe(getViewLifecycleOwner(), new Observer<List<Photo.HitsBean>>() {
            @Override
            public void onChanged(List<Photo.HitsBean> hitsBeans) {
                adapter.submitList(hitsBeans);
                recyclerView.scrollToPosition(0);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                keyWord = query;
                viewModel.cleanOldData();
                viewModel.getPhotoLiveData(keyWord);
                Log.e("myTAG", "onQueryTextSubmit: ");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        adapter.setOnItemClickListener((view1, position) -> {
            Bundle bundle = new MainFragmentArgs.Builder().setCurrentPhoto(position).build().toBundle();
            Navigation.findNavController(view1).navigate(R.id.action_mainFragment_to_detailFragment, bundle);
        });

        if (adapter.getCurrentList().isEmpty()) {
            recyclerView.setVisibility(View.GONE);
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("myTag", "onScrolled: " + dy);
                if (dy < 0) return;
                int[] array = new int[3];
                ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPositions(array);
                if (array[0] == adapter.getItemCount() - 1) {
                    viewModel.getPhotoLiveData(keyWord);
                    Log.e("myTag", "onScroll");
                }
            }
        });
        return view;
    }
}
