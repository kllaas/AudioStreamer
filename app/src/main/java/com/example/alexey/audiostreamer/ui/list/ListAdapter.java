package com.example.alexey.audiostreamer.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexey.audiostreamer.R;
import com.example.alexey.audiostreamer.data.Track;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexey
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Track> tracks;

    private OnClick onClick;

    @Inject
    ListAdapter(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_track, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(tracks.get(position));
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    void setOnItemClick(OnClick onItemClick) {
        this.onClick = onItemClick;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        void onBind(Track news) {
            // fill views with content
        }
    }

}
