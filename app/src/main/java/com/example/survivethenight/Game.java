package com.example.survivethenight;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {

    private boolean shot = false;
    public int counter =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final MediaPlayer gunShot = MediaPlayer.create(this, R.raw.gun);

        final TextView tV = findViewById(R.id.time2);

        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                tV.setText((String.valueOf(counter)));
                counter++;
                if(counter ==5)
                    gunShot.start();
                    if(counter ==6 && !shot) {
                        tV.setText("you lose");
                    }


            }

            @Override
            public void onFinish() {

            }
        }.start();


        final MediaPlayer backG = MediaPlayer.create(this, R.raw.walking);
       // final MediaPlayer kill1 = MediaPlayer.create(this, R.raw.gun);

        backG.start();



        final Button bt = findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shot = true;
                gunShot.start();

            }
        });


    }

}
