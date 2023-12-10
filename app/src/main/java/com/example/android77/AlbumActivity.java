package com.example.android77;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlbumActivity extends AppCompatActivity {

    private EditText editTextAlbumName;
    private Button buttonCreateAlbum;
    private Button buttonOpenAlbum;
    private Button buttonDeleteAlbum;
    private Button buttonRenameAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        editTextAlbumName = findViewById(R.id.editTextAlbumName);
        buttonCreateAlbum = findViewById(R.id.buttonCreateAlbum);
        buttonOpenAlbum = findViewById(R.id.buttonOpenAlbum);
        buttonDeleteAlbum = findViewById(R.id.buttonDeleteAlbum);
        buttonRenameAlbum = findViewById(R.id.buttonRenameAlbum);

        // Create album
        buttonCreateAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String albumName = editTextAlbumName.getText().toString();
                createAlbum(albumName);
            }
        });

        // Open album
        buttonOpenAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String albumName = editTextAlbumName.getText().toString();
                openAlbum(albumName);
            }
        });

        // Delete album
        buttonDeleteAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String albumName = editTextAlbumName.getText().toString();
                deleteAlbum(albumName);
            }
        });

        // Rename album
        buttonRenameAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String albumName = editTextAlbumName.getText().toString();
                renameAlbum(albumName);
            }
        });
    }

    private void createAlbum(String albumName) {
        // Logic to create an album
        Toast.makeText(this, "Album Created: " + albumName, Toast.LENGTH_SHORT).show();
    }

    private void openAlbum(String albumName) {
        // Logic to open an album
        Toast.makeText(this, "Opening Album: " + albumName, Toast.LENGTH_SHORT).show();
    }

    private void deleteAlbum(String albumName) {
        // Logic to delete an album
        Toast.makeText(this, "Album Deleted: " + albumName, Toast.LENGTH_SHORT).show();
    }

    private void renameAlbum(String albumName) {
        // Logic to rename an album
        Toast.makeText(this, "Album Renamed: " + albumName, Toast.LENGTH_SHORT).show();
    }
}
