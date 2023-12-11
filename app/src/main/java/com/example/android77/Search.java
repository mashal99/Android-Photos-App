/*
 * Name: Ahmed Mashaal
 * ID: amm884
 */

package com.example.android77;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Search extends AppCompatActivity {
    // Declaration of UI elements
    private RadioButton person;
    private RadioGroup rg;
    private EditText tagData;
    private Button search, cancel;
    private int type = -1;
    private static ArrayList<Photo> searched = new ArrayList<>();
    private static ArrayList<Photo> sList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initializing UI components and setting up listeners
        initializeViews();
        setButtonListeners();

        // Reading existing data
        readAlbumData();
    }
    // Initialize UI components by finding them in the layout
    private void initializeViews() {
        rg = findViewById(R.id.radiogroup);
        person = findViewById(R.id.person);
        tagData = findViewById(R.id.data);
        search = findViewById(R.id.search);
        cancel = findViewById(R.id.cancel);
    }


    // Set listeners for buttons
    private void setButtonListeners() {
        // Listener for the cancel button
        cancel.setOnClickListener(view -> finish());

        // Listener for the search button
        search.setOnClickListener(view -> {
            searched.clear();
            sList.clear();
            readAlbumData();
            performSearch();
        });
    }

    // Handles the search functionality
    private void performSearch() {
        type = rg.getCheckedRadioButtonId();
        if (!tagData.getText().toString().equals("")) {
            String tagType = (type == R.id.location) ? "Location" : "Person";
            addPhotosToList(tagType);
            write();
            finish();
            startAlbumView();
        }
    }

    // Adds photos to the list based on the specified tag type
    private void addPhotosToList(String tagType) {
        for (Photo p : searched) {
            for (Tag t : p.tags) {
                if (t.getData().contains(tagData.getText().toString()) && t.type.equals(tagType)) {
                    sList.add(p);
                    break;
                }
            }
        }
    }

    // Starts the AlbumView activity
    private void startAlbumView() {
        Intent intent = new Intent(getApplicationContext(), AlbumView.class);
        HomeScreen.albumName = "SearchRes";
        startActivity(intent);
    }

    // Reads the images from storage
    private void readAlbumData() {
        try (FileInputStream fileInputStream = openFileInput("albums.albm");
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String lineIn;
            while ((lineIn = bufferedReader.readLine()) != null) {
                processAlbumFile(lineIn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processAlbumFile(String albumName) {
        try (FileInputStream fileInputStream = openFileInput(albumName + ".list");
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String lineIn;
            while ((lineIn = bufferedReader.readLine()) != null) {
                if (lineIn.startsWith("TAG:")) {
                    searched.get(searched.size() - 1).addTag(lineIn.substring(4));
                } else {
                    searched.add(new Photo(Uri.parse(lineIn)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Writes search results to a file
    public void write() {
        try (FileOutputStream fileOutputStream = openFileOutput("SearchRes.list", MODE_PRIVATE)) {
            StringBuilder strBuilder = new StringBuilder();
            for (Photo u : sList) {
                strBuilder.append(u.getUri().toString()).append("\n");
                for (Tag t : u.tags) {
                    strBuilder.append("TAG:").append(t.toString()).append("\n");
                }
            }
            fileOutputStream.write(strBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}