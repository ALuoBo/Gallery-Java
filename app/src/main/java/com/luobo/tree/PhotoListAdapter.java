package com.luobo.tree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.luobo.tree.repository.Photo;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

class PhotoListAdapter extends ListAdapter<Photo.HitsBean, PhotoListAdapter.HitViewHolder> {

    private Context context;

    private OnItemClickListener onItemClickListener;

    protected PhotoListAdapter(Context context, @NonNull DiffUtil.ItemCallback<Photo.HitsBean> diffCallback) {
        super(diffCallback);
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    class HitViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public HitViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }

    }

    private final LayoutInflater mInflater;

    @NonNull
    @Override
    public HitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item_layout, parent, false);
        return new HitViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull HitViewHolder holder, int position) {
        Glide.with(context)
                .load(getItem(position).getPreviewURL())
                .placeholder(R.drawable.ic_baseline_photo_24)
                .centerCrop()
                .transition(withCrossFade())
                .into(holder.imageView);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemListener(holder.itemView, position);
                }
            });
        }
    }

   interface OnItemClickListener {
        void onItemListener(View view, int position);
    }
}