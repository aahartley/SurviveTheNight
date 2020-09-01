package com.example.survivethenight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speakIntro();
        final Button startBt = findViewById(R.id.startBt);

       startBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.stop();
                openGame();
            }
        });


    }
    public void openGame(){
        Intent intent = new Intent(this,Game.class);
        startActivity(intent);
        finish();
    }
    public void speakIntro(){
        final String msg ="Welcome to Survive the Night, the premise is simple"+
                " you must survive the wild equipped only with a revolver with two bullets left and only one reload of six available" +
                ", tap anywhere to shoot and shake to reload, use them wisely as only certain noises will kill you, good luck";
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.ENGLISH);
                    textToSpeech.setSpeechRate(.90f);
                textToSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH, null);

                }
            }
        });

    }

}