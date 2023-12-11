/*
 * Name: Ahmed Mashaal
 * ID: amm884
 */

package com.example.android77;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NewTag extends AppCompatActivity {
    private RadioButton loc, person;
    private RadioGroup rg;
    private EditText tagData;
    private Button send, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tag);

        rg = findViewById(R.id.radiogroup);

        loc = findViewById(R.id.location);
        person = findViewById(R.id.person);

        tagData = findViewById(R.id.data);
        send = findViewById(R.id.add);
        cancel = findViewById(R.id.cancel);

        cancel.setOnClickListener(view -> finish());

        send.setOnClickListener(view -> {
            String newTagData = tagData.getText().toString();
            if (newTagData.equals("")) {
                Toast.makeText(getApplicationContext(), "Please enter a tag", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedId = rg.getCheckedRadioButtonId();
            String tagType = selectedId == R.id.location ? "Location=" : "Person=";
            String fullTag = tagType + newTagData;

            if (isTagExists(fullTag)) {
                Toast.makeText(getApplicationContext(), "This tag already exists", Toast.LENGTH_SHORT).show();
            } else {
                ImageAdapter.uris.get(SlideShowView.index).addTag(fullTag);
                SlideShowView.gridView.setAdapter(SlideShowView.tagAdapter);
                write();
                finish();
            }
        });
    }

    private boolean isTagExists(String tag) {
        ArrayList<String> tags = new ArrayList<>();

        for (Tag t : ImageAdapter.uris.get(SlideShowView.index).tags) {
            tags.add(t.toString());
        }
        return tags.contains(tag);
    }

    public void write() {
        try {
            ArrayList<Photo> uris = AlbumView.imgAdapter.getPhotos();
            StringBuilder str = new StringBuilder();
            FileOutputStream fileOutputStream = openFileOutput(HomeScreen.albumName + ".list", MODE_PRIVATE);
            for (Photo u : uris) {
                if (str.length() > 0) {
                    str.append("\n");
                }
                str.append(u.getUri().toString());
                for (Tag t : u.tags) {
                    str.append("\nTAG:").append(t.toString());
                }
            }
            fileOutputStream.write(str.toString().getBytes());
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }
    }
}
