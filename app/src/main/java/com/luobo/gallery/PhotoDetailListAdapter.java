package com.luobo.gallery;

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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.luobo.gallery.repository.Photo;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

class PhotoDetailListAdapter extends ListAdapter<Photo.HitsBean, PhotoDetailListAdapter.MyViewHolder> {

    private Context context;

    private OnItemClickListener onItemClickListener;

    private static final int NORMAL_VIEW_TYPE = 0;

    private static final int FOOTER_VIEW_TYPE = 1;

    protected PhotoDetailListAdapter(Context context, @NonNull DiffUtil.ItemCallback<Photo.HitsBean> diffCallback) {
        super(diffCallback);
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private final LayoutInflater mInflater;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == FOOTER_VIEW_TYPE) {
            itemView = mInflater.inflate(R.layout.footer_layout, parent, false);

        } else {

            itemView = mInflater.inflate(R.layout.recyclerview_detail_item, parent, false);

        }
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (getItemCount() - 1 != position) {
            ImageView imageView = holder.itemView.findViewById(R.id.imageView_detail);
            Glide.with(context)
                    .load(getItem(position).getLargeImageURL())
                    .centerCrop()
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(v -> {
                    int position1 = holder.getLayoutPosition();
                    onItemClickListener.onItemListener(holder.itemView, position1);
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTER_VIEW_TYPE;
        } else return NORMAL_VIEW_TYPE;
    }

    interface OnItemClickListener {
        void onItemListener(View view, int position);
    }
}