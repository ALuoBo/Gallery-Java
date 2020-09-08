package com.luobo.tree;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.luobo.tree.repository.Photo;

class PhotoDiffUtil extends DiffUtil.ItemCallback<Photo.HitsBean> {

    @Override
    public boolean areItemsTheSame(@NonNull Photo.HitsBean oldItem, @NonNull Photo.HitsBean newItem) {
        return oldItem==newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Photo.HitsBean oldItem, @NonNull Photo.HitsBean newItem) {
        return false;
    }
}
