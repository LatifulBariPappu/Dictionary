package com.example.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText searchTv;
    TextView word,definitionTv,exampleTv,originTv;
    ImageView searchBtn,micBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchTv=findViewById(R.id.searchTv);
        word=findViewById(R.id.word_id);
        definitionTv=findViewById(R.id.definitionTv);
        exampleTv=findViewById(R.id.exampleTv);
        originTv=findViewById(R.id.originTv);
        searchBtn=findViewById(R.id.SearchBtn);
        micBtn=findViewById(R.id.speakBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void getMeaning(String main_word){


    }
}