package com.example.survivethenight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 5 seconds
                speakIntro();

            }
        }, 500);
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
        final String msg ="Welcome to Survive the Night, the premise is simple. "+
                "You are equipped with a revolver to scare away the local wildlife. " +
                "You currently have two bullets in the cylinder. You can reload once for an " +
                "additional six bullets. Only certain noises will kill you, so use them wisely. " +
                "Tap anywhere to shoot. Shake to reload. " +
                "The game will start when you're ready to begin . Good luck!";
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