package com.luobo.gallery;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.luobo.gallery.repository.Photo;

class PhotoDiffUtil extends DiffUtil.ItemCallback<Photo.HitsBean> {

    @Override
    public boolean areItemsTheSame(@NonNull Photo.HitsBean oldItem, @NonNull Photo.HitsBean newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Photo.HitsBean oldItem, @NonNull Photo.HitsBean newItem) {
        return oldItem.getPageURL().equals(newItem.getPageURL());
    }
}
