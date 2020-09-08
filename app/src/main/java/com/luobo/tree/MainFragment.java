package com.luobo.tree;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luobo.tree.repository.Photo;


public class MainFragment extends Fragment {
    private PhotoViewModel viewModel;
    private SearchView searchView;


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PhotoViewModel.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        searchView = view.findViewById(R.id.searchView);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        PhotoListAdapter adapter = new PhotoListAdapter(getContext(), new PhotoDiffUtil());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

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

        adapter.setOnItemClickListener(new PhotoListAdapter.OnItemClickListener() {
            @Override
            public void onItemListener(View view, int position) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_detailFragment);
            }
        });
        return view;
    }


}