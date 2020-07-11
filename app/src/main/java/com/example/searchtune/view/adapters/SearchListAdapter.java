package com.example.searchtune.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.searchtune.R;
import com.example.searchtune.services.model.Results;

import java.util.LinkedList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.SearchListHolder> {

    public SearchListAdapter(Context context) {
        this.context = context;
    }

    private Context context;
    private List<Results> resultsList = new LinkedList<>();

    public void setItems(List<Results> resultsList) {
        this.resultsList = resultsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_item_view, parent, false);
        return new SearchListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchListHolder holder, int position) {
        Results results = resultsList.get(position);
        Glide.with(context).load(results.getArtworkUrl100()).into(holder.iv_albumArt);
        holder.tv_artistName.setText(String.format("Artist: %s", results.getArtistName()));
        holder.tv_price.setText(String.format("Price: $ %s", results.getTrackPrice()));
        holder.tv_genre.setText(String.format("Genre: %s", results.getPrimaryGenreName()));
        holder.tv_trackName.setText(String.format("Track: %s", results.getTrackName()));
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class SearchListHolder extends RecyclerView.ViewHolder {

        CircleImageView iv_albumArt;
        TextView tv_trackName;
        TextView tv_artistName;
        TextView tv_price;
        TextView tv_genre;

        public SearchListHolder(@NonNull View itemView) {
            super(itemView);
            iv_albumArt = itemView.findViewById(R.id.iv_album_art);
            tv_artistName = itemView.findViewById(R.id.tv_artist_name);
            tv_trackName = itemView.findViewById(R.id.tv_track_name);
            tv_genre = itemView.findViewById(R.id.tv_genre);
            tv_price = itemView.findViewById(R.id.tv_price);

        }
    }
}
