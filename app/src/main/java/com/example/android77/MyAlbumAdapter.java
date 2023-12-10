package com.example.android77;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAlbumAdapter extends RecyclerView.Adapter<MyAlbumAdapter.AlbumViewHolder> {

    private ArrayList<String> albumNames;

    // Constructor
    public MyAlbumAdapter(ArrayList<String> albumNames) {
        this.albumNames = albumNames;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new AlbumViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the contents of the view
        String albumName = albumNames.get(position);
        holder.textView.setText(albumName);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return albumNames.size();
    }

    // Provide a reference to the views for each data item
    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public AlbumViewHolder(View v) {
            super(v);
            textView = v.findViewById(android.R.id.text1);
        }
    }
}
