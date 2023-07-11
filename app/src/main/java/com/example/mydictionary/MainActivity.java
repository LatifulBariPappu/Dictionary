package com.example.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText searchTv;
    TextView word,definitionTv,partofSpeechTv,synonyms,examples;
    ImageView searchBtn,micBtn;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchTv=findViewById(R.id.searchTv);
        word=findViewById(R.id.word_id);
        definitionTv=findViewById(R.id.definitionTv);
        partofSpeechTv=findViewById(R.id.psTv);
        synonyms=findViewById(R.id.synonymTv);
        searchBtn=findViewById(R.id.SearchBtn);
        micBtn=findViewById(R.id.speakBtn);
        examples=findViewById(R.id.exampleTv);

        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchWord=searchTv.getText().toString().trim();
                getInfo(searchWord);
            }
        });

        micBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String toSpeak=definitionTv.getText().toString();
               textToSpeech.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }
    private void getInfo(String main_word){

        String URL="https://api.dictionaryapi.dev/api/v2/entries/en/"+main_word;

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject=response.getJSONObject(0);
                    String word_d=jsonObject.getString("word");
                    word.setText(word_d);


                    JSONArray jsonArray1=jsonObject.getJSONArray("meanings");
                    JSONObject jsonObject2=jsonArray1.getJSONObject(0);
                    String synonym=jsonObject2.getString("synonyms");

                    JSONArray jsonArray2=jsonObject2.getJSONArray("definitions");
                    JSONObject jsonObject3=jsonArray2.getJSONObject(0);

                    String mean=jsonObject3.getString("definition");
                    String example=jsonObject3.getString("example");
                    String partofSpeech=jsonObject2.getString("partOfSpeech");
                    definitionTv.setText(mean);
                    partofSpeechTv.setText(partofSpeech);
                    synonyms.setText(synonym);
                    examples.setText(example);

                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);


    }
}