package com.luobo.tree;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import com.bumptech.glide.Glide;
import com.luobo.tree.repository.Photo;

class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.HitViewHolder> {

    private Photo photos; // Cached copy of Photos
    private Context context;

    class HitViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView, imageView2, imageView3;

        public HitViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);


        }

    }

    private final LayoutInflater mInflater;

    PhotoListAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public HitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item_layout, parent, false);
        return new HitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HitViewHolder holder, int position) {
        Glide.with(context)
                .load(photos.getHits().get(position).getLargeImageURL())
                .placeholder(R.drawable.ic_baseline_photo_24)
                .centerCrop()
                .transition(withCrossFade())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if (photos != null)
            return photos.getHits().size();
        else return 0;
    }

    void setPhotos(Photo photos) {
        this.photos = photos;
        Log.d("MyTag", photos.getHits().toString());
        notifyDataSetChanged();

    }
}
