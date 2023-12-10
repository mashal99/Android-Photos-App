package com.example.android77;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {

    private ArrayList<Integer> photoIds; // Assuming drawable IDs for simplicity
    private ImageView imageViewPhoto;
    private int currentPhotoIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imageViewPhoto = findViewById(R.id.imageViewPhoto);

        // Initialize your photo list
        photoIds = new ArrayList<>();
        // ... add more photos as needed

        displayPhoto(currentPhotoIndex);

        findViewById(R.id.buttonNextPhoto).setOnClickListener(v -> displayNextPhoto());
        findViewById(R.id.buttonPreviousPhoto).setOnClickListener(v -> displayPreviousPhoto());
        findViewById(R.id.buttonAddPhoto).setOnClickListener(v -> addPhoto());
        findViewById(R.id.buttonRemovePhoto).setOnClickListener(v -> removePhoto());
    }

    private void displayPhoto(int index) {
        if (!photoIds.isEmpty() && index < photoIds.size() && index >= 0) {
            imageViewPhoto.setImageResource(photoIds.get(index));
        }
    }

    private void displayNextPhoto() {
        if (currentPhotoIndex < photoIds.size() - 1) {
            currentPhotoIndex++;
            displayPhoto(currentPhotoIndex);
        } else {
            Toast.makeText(this, "No more photos", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayPreviousPhoto() {
        if (currentPhotoIndex > 0) {
            currentPhotoIndex--;
            displayPhoto(currentPhotoIndex);
        } else {
            Toast.makeText(this, "This is the first photo", Toast.LENGTH_SHORT).show();
        }
    }

    private void addPhoto() {
        // Logic to add a photo
        // This could be an intent to open a gallery, or a dialog to enter a photo URL
    }

    private void removePhoto() {
        if (!photoIds.isEmpty() && currentPhotoIndex < photoIds.size()) {
            photoIds.remove(currentPhotoIndex);
            if (photoIds.size() == currentPhotoIndex) { // If last photo was removed
                currentPhotoIndex--;
            }
            displayPhoto(currentPhotoIndex);
        } else {
            Toast.makeText(this, "No photos to remove", Toast.LENGTH_SHORT).show();
        }
    }
}
