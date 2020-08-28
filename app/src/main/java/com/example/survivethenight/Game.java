package com.example.survivethenight;

import android.content.Intent;
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
    MediaPlayer mP;
    MediaPlayer mP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mP = MediaPlayer.create(this,R.raw.walking);
        mP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mP.release();
                mP = MediaPlayer.create(Game.this, R.raw.bg);
                mP.start();


            }
        });
        mP.start();


        final TextView tV = findViewById(R.id.time2);

        new CountDownTimer(20000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                tV.setText((String.valueOf(counter)));
                counter++;
                if(counter ==5){
                    mP2 = MediaPlayer.create(Game.this, R.raw.gun);
                    mP2.start();
                }
                    if(counter ==6 && !shot) {
                        mP.stop();
                        cancel();
                        endGame();
                        finish();
                    }
                    if(counter == 10) {
                        shot = false;
                        mP2.start();
                    }
                         if(counter ==12 && !shot) {
                            tV.setText(R.string.youLose);
                            cancel();
                }



            }

            @Override
            public void onFinish() {

            }
        }.start();





        final Button bt = findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shot = true;
                mP2 = MediaPlayer.create(Game.this,R.raw.gun);
                mP2.start();

            }
        });


    }
    public void endGame(){
        Intent intent = new Intent(this,End.class);
        startActivity(intent);
        finish();
    }

}
