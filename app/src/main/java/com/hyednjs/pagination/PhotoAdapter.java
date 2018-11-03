package com.hyednjs.pagination;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.flickr4java.flickr.photos.Photo;
import com.hyednjs.R;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoAdapter extends PagedListAdapter<Photo, PhotoAdapter.PhotoViewHolder> {

    private static final DiffUtil.ItemCallback<Photo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.getUrl() == newItem.getUrl();
        }
    };

    public interface OnClickListener {
        public void onClick(View view, Photo photo);
    }

    private OnClickListener onClickListener = null;
    public PhotoAdapter() {
        super(DIFF_CALLBACK);
    }

    private View.OnClickListener _onClickListener = view -> {
        if (onClickListener != null) {
            int position = (int)view.getTag();
            onClickListener.onClick(view, getItem(position));
        }
    };

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = getItem(position);
        holder.draweeView.setImageURI(Uri.parse(photo.getSmallUrl()));
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(_onClickListener);
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView draweeView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            draweeView = itemView.findViewById(R.id.draweeView);
            draweeView.setAspectRatio(1);
            draweeView.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
