package com.example.android77;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> albumNames; // List to store album names
    private RecyclerView recyclerView; // RecyclerView to display albums
    private RecyclerView.Adapter adapter; // Adapter for RecyclerView
    private RecyclerView.LayoutManager layoutManager; // LayoutManager for RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the album list
        albumNames = new ArrayList<>();
        loadAlbums(); // Load albums

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerViewAlbums);
        recyclerView.setHasFixedSize(true); // Improves performance if changes in content do not change the layout size
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAlbumAdapter(albumNames); // Replace MyAlbumAdapter with your actual adapter class
        recyclerView.setAdapter(adapter);

        // Button to navigate to AlbumActivity
        Button btnOpenAlbumActivity = findViewById(R.id.btnOpenAlbumActivity);
        btnOpenAlbumActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
                startActivity(intent);
            }
        });

        // Additional setup as needed
    }

    private void loadAlbums() {
        // Placeholder method to load album names
        albumNames.add("Album 1");
        albumNames.add("Album 2");
        albumNames.add("Album 3");
        // Note: With RecyclerView, you might want to notify the adapter of the data change
        // adapter.notifyDataSetChanged(); // Uncomment this if you update the list after setting the adapter
    }
}
