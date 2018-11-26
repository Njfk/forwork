package com.forwork.com.forwork.ui.fragment.department.depart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.forwork.com.forwork.R;
import com.forwork.com.forwork.bean.base.Music;
import com.forwork.com.forwork.net.ListApi;

import java.util.List;

/**
 * Created by Administrator on 2018/11/26.
 */

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{
    List<Music> musicList;
    Context context;
    LayoutInflater inflater;
    OnMusicItemClickLisenter onMusicItemClickLisenter;

    public MusicListAdapter(List<Music> musicList, Context context) {
        this.musicList = musicList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.item_music_list, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).asBitmap().apply(new RequestOptions().centerCrop().placeholder(R.drawable.ic_hot_music_type_default)).load(musicList.get(position).getSingerSmallImg())
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMusicItemClickLisenter.onItemClick(position);
            }
        });
        holder.content.setText(musicList.get(position).getTitle()+";"+musicList.get(position).getSinger()+";"+musicList.get(position).getAlbum());
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView content;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.music_icon);
            content = itemView.findViewById(R.id.music_content);
        }
    }

    public interface OnMusicItemClickLisenter{
        void onItemClick(int position);
    }

    public OnMusicItemClickLisenter getOnMusicItemClickLisenter() {
        return onMusicItemClickLisenter;
    }

    public void setOnMusicItemClickLisenter(OnMusicItemClickLisenter onMusicItemClickLisenter) {
        this.onMusicItemClickLisenter = onMusicItemClickLisenter;
    }
}
