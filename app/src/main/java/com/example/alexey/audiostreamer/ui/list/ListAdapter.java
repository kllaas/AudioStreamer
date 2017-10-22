package com.example.alexey.audiostreamer.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alexey.audiostreamer.R;
import com.example.alexey.audiostreamer.data.entity.Station;
import com.example.alexey.audiostreamer.utils.custom_view.SquareImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexey
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Station> stations;

    private OnClick onClick;

    @Inject
    ListAdapter(List<Station> stations) {
        this.stations = stations;
    }

    void setItems(List<Station> stations) {
        this.stations = stations;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_station, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(stations.get(position));
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    void setOnItemClick(OnClick onItemClick) {
        this.onClick = onItemClick;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        SquareImageView image;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.categories)
        TextView categories;

        @BindView(R.id.listeners)
        TextView listeners;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        void onBind(Station station) {
            name.setText(station.getName());
            categories.setText(station.getCategoriesString());
            listeners.setText(itemView.getContext().getString(R.string.n_list, station.getListeners()));

            if (station.getImage() != null && station.getImage().getUrlToImage() != null)
                Glide.with(itemView.getContext())
                        .load(station.getImage().getUrlToImage())
                        .asBitmap()
                        .fitCenter()
                        .into(image);
        }

    }

}
