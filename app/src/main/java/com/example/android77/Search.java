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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Search extends AppCompatActivity {
    private RadioButton loc, person;
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

        initializeViews();
        setButtonListeners();
        read();
    }

    private void initializeViews() {
        rg = findViewById(R.id.radiogroup);
        loc = findViewById(R.id.location);
        person = findViewById(R.id.person);
        tagData = findViewById(R.id.data);
        search = findViewById(R.id.search);
        cancel = findViewById(R.id.cancel);
    }

    private void setButtonListeners() {
        cancel.setOnClickListener(view -> finish());

        search.setOnClickListener(view -> {
            ArrayList<String> tags = new ArrayList<>();
            searched.clear();
            sList.clear();
            read();
            performSearch();
        });
    }

    private void performSearch() {
        type = rg.getCheckedRadioButtonId();
        if (!tagData.getText().toString().equals("")) {
            switch (type) {
                case R.id.loc: // Use resource IDs instead of hardcoded values
                    addPhotosToList("Location");
                    break;
                case R.id.person:
                    addPhotosToList("Person");
                    break;
                default:
                    break;
            }
            write();
            finish();
            startAlbumView();
        }
    }

    private void addPhotosToList(String tagType) {
        for (Photo p : searched) {
            for (Tag t : p.tags) {
                if (t.getData().contains(tagData.getText().toString()) &&
                        t.type.equals(tagType)) {
                    sList.add(p);
                    break;
                }
            }
        }
    }

    private void startAlbumView() {
        Intent intent = new Intent(getApplicationContext(), AlbumView.class);
        HomeScreen.albumName = "SearchRes";
        startActivity(intent);
    }

    public void read() {
        ArrayList<String> masterList = new ArrayList<>();

        try (FileInputStream fileInputStream = openFileInput("albums.albm");
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String lineIn;
            while ((lineIn = bufferedReader.readLine()) != null) {
                processLine(lineIn, masterList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String albumName, ArrayList<String> masterList) {
        try (FileInputStream fileInputStream2 = openFileInput(albumName + ".list");
             InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream2);
             BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2)) {

            String lineIn;
            while ((lineIn = bufferedReader2.readLine()) != null) {
                masterList.add(lineIn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String input : masterList) {
            if (input.startsWith("TAG:")) {
                searched.get(searched.size() - 1).addTag(input.substring(4));
            } else {
                searched.add(new Photo(Uri.parse(input)));
            }
        }
    }

    public void write() {
        try (FileOutputStream fileOutputStream = openFileOutput("SearchRes.list", MODE_PRIVATE)) {
            StringBuilder strBuilder = new StringBuilder();
            for (Photo u : sList) {
                ArrayList<String> tgs = new ArrayList<>();
                strBuilder.append(u.getUri().toString()).append("\n");
                for (Tag t : u.tags) {
                    if (!tgs.contains(t.toString())) {
                        strBuilder.append("TAG:").append(t.toString()).append("\n");
                        tgs.add(t.toString());
                    }
                }
            }
            fileOutputStream.write(strBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
