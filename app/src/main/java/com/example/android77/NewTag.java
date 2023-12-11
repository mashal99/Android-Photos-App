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
    private int type = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tag);

        rg = (RadioGroup) findViewById(R.id.radiogroup);

        loc = (RadioButton) findViewById(R.id.location);
        person = (RadioButton) findViewById(R.id.person);

        tagData = (EditText) findViewById(R.id.data);
        send = (Button) findViewById(R.id.add);
        cancel = (Button) findViewById(R.id.cancel);

        cancel.setOnClickListener(view -> finish());

        send.setOnClickListener(view -> {
            ArrayList<String> tags = new ArrayList<>();

            for (Tag t: ImageAdapter.uris.get(SlideShowView.index).tags) {
                if (!(tags.contains(t.toString()))){
                    tags.add(t.toString());
                }
            }

            type = rg.getCheckedRadioButtonId();
            if (!tagData.getText().toString().equals("")) {
                switch (type) {
                    case 2131165275:
                        if (tags.contains("Location=" + tagData.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "This tag already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            ImageAdapter.uris.get(SlideShowView.index).addTag("Location=" + tagData.getText().toString()); SlideShowView.gridView.setAdapter(SlideShowView.tagAdapter);
                            write();
                            finish();
                        }

                        break;
                    case 2131165294:
                        if (tags.contains("Person=" + tagData.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "This tag already exists", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            ImageAdapter.uris.get(SlideShowView.index).addTag("Person=" + tagData.getText().toString());
                            SlideShowView.gridView.setAdapter(SlideShowView.tagAdapter);
                            write();
                            finish();
                        }
                        break;
                    default:
                        break;

                }
            }
        });
    }

    public void write(){
        try {
            ArrayList<Photo> uris = AlbumView.imgAdapter.getPhotos();

            String str = "";
            FileOutputStream fileOutputStream = openFileOutput(HomeScreen.albumName+".list", MODE_PRIVATE);
            for (Photo u : uris) {
                if (str.equals("")) {
                    str = u.getUri().toString();

                }
                else {
                    str = str + "\n" + u.getUri().toString();
                }
                for (Tag t : u.tags){
                    str = str + "\nTAG:" + t.toString();
                }
            }
            fileOutputStream.write(str.getBytes());

        }
        catch(ArrayIndexOutOfBoundsException | IOException e){
            e.printStackTrace();
        }
    }
}
