package com.luobo.tree;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.luobo.tree.repository.Photo;


public class MainFragment extends Fragment {
    private PhotoViewModel viewModel;
    private SearchView searchView;

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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.getPhotoLiveData(query).observe(getViewLifecycleOwner(), new Observer<Photo>() {
                    @Override
                    public void onChanged(Photo photo) {
                        adapter.submitList(photo.getHits());
                    }

                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        adapter.setOnItemClickListener((view1, position) -> Navigation.findNavController(view1).navigate(R.id.action_mainFragment_to_detailFragment));
        return view;
    }


}