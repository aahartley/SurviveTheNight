package com.example.survivethenight;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class End extends AppCompatActivity {
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Intent intent = getIntent();
        boolean win = intent.getExtras().getBoolean("win");

        if (win) {
            speakWin();


        }
        else{
            speakLose();

        }
        final  Button bt = findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.stop();
                startOver();
            }
        });

        bt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish();
                return true;
            }
        });

    }


    public void startOver(){
        Intent intent = new Intent(this,Game.class);
        startActivity(intent);
    }
    public void speakWin(){
        final String msg ="Congratulations you have survived the night. Long press to end the game";
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
    public void speakLose(){
        final String msg ="Unfortunately, you did not survive the night. You can tap to restart or long press to end the game";
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
