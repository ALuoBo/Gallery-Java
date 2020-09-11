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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.luobo.tree.repository.Photo;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

class PhotoListAdapter extends ListAdapter<Photo.HitsBean, PhotoListAdapter.HitViewHolder> {

    private static final int NORMAL_VIEW_TYPE = 0;
    private static final int FOOTER_VIEW_TYPE = 1;

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
        public HitViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    private final LayoutInflater mInflater;

    @NonNull
    @Override
    public HitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == FOOTER_VIEW_TYPE) {
            itemView = mInflater.inflate(R.layout.footer_layout, parent, false);
            ((StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams()).setFullSpan(true);
        } else {
            itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);

        }
        return new HitViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull HitViewHolder holder, int position) {
        if (position != getItemCount() - 1) {
            ImageView imageView = holder.itemView.findViewById(R.id.itemImageView);

            Glide.with(context)
                    .load(getItem(position).getWebformatURL())
                    .centerCrop()
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemListener(holder.itemView, position);
                    }
                });
            }
        } else {
            LottieAnimationView lottieAnimationView = holder.itemView.findViewById(R.id.lottieAnimationView);
            lottieAnimationView.playAnimation();
        }
    }

    interface OnItemClickListener {
        void onItemListener(View view, int position);
    }

    @Override
    public int getItemCount() {
        //添加一个底部，所以条目总数加1
        return super.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        //getItemCount()-1代表最后一个条目
        if (position == getItemCount() - 1) {
            return FOOTER_VIEW_TYPE;
        } else return NORMAL_VIEW_TYPE;
    }
}