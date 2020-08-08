package com.inreadyworkgroup.newsapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.inreadyworkgroup.newsapp.Model.News;
import com.inreadyworkgroup.newsapp.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<News> mKontakList;
    lonceng mLonceng;

    public NewsAdapter(List<News> mKontakList) {
        this.mKontakList = mKontakList;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
        ViewHolder mViewHolder = new ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, final int position) {


        holder.mTextViewId.setText(mKontakList.get(position).getTitle());
        Glide.with(holder.itemView)
            .load(mKontakList.get(position).getUrlToImage())
            .into(holder.mImageViewId);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLonceng.onClickItem(mKontakList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return  mKontakList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId;
        public ImageView mImageViewId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewId = (TextView) itemView.findViewById(R.id.tv_judul);
            mImageViewId = (ImageView) itemView.findViewById(R.id.tv_img);
        }
    }

    public interface lonceng {
        void onClickItem(News data);
    }

    public void setOnChangeItem(lonceng listener) {
        mLonceng = listener;

    }
}
