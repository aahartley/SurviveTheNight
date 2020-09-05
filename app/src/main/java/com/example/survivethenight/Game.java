package com.example.survivethenight;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class Game extends AppCompatActivity implements SensorEventListener {
    public int bullets =2;
    private boolean shot = false;
    private boolean win = false;
    public int counter =0;
    private SensorManager sM;
    private Sensor aS;
    private boolean aSA;
    private boolean notFirst = false;
    private float lastX;
    private float lastY;
    private float lastZ;
    public boolean reload = false;
    MediaPlayer mP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sM = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        assert sM != null;
        if(sM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null){
            aS = sM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            aSA = true;
        }
        else{
            aSA=false;
        }

        mP = MediaPlayer.create(Game.this,R.raw.background);
        mP.start();


        final TextView tV = findViewById(R.id.time2);
        final TextView tv2 = findViewById(R.id.time3);

        new CountDownTimer(112000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                tV.setText((String.valueOf(counter)));
                tv2.setText(String.valueOf(bullets));

                counter++;

                if(reload){
                  reload();
                   reload = false;
                }

                if(counter ==1){
                    shot = false;
                }
                    if(counter ==3&& !shot) {
                        mP.release();
                        die();
                        cancel();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                // Actions to do after 5 seconds
                                endGame();
                            }
                        }, 3000);
                    }
                    if(counter == 12) {
                        shot = false;
                    }
                         if(counter ==14 && !shot) {
                             mP.release();
                             die();
                             cancel();
                             Handler handler = new Handler();
                             handler.postDelayed(new Runnable() {
                                 public void run() {
                                     // Actions to do after 5 seconds
                                     endGame();
                                 }
                             }, 3000);
                }
                if(counter ==28){
                    shot = false;
                }
                if(counter ==30&& !shot) {
                    mP.release();
                    die();
                    cancel();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 5 seconds
                            endGame();
                        }
                    }, 3000);
                }
                if(counter ==42){
                    shot = false;
                }
                if(counter ==44&& !shot) {
                    mP.release();
                    die();
                    cancel();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 5 seconds
                            endGame();
                        }
                    }, 3000);
                }
                if(counter ==56){
                    shot = false;
                }
                if(counter ==58&& !shot) {
                    mP.release();
                    die();
                    cancel();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 5 seconds
                            endGame();
                        }
                    }, 3000);
                }
                if(counter ==70){
                    shot = false;
                }
                if(counter ==72&& !shot) {
                    mP.release();
                    die();
                    cancel();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 5 seconds
                            endGame();
                        }
                    }, 3000);
                }
                if(counter ==104){
                    shot = false;
                }
                if(counter ==106&& !shot) {
                    mP.release();
                    die();
                    cancel();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 5 seconds
                            endGame();
                        }
                    }, 3000);
                }



            }

            @Override
            public void onFinish() {
                win = true;
                mP.release();
                endGame();

            }
        }.start();





        final Button bt = findViewById(R.id.button);



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    MediaPlayer mP2;

                    if (bullets > 0) {
                        mP2 = MediaPlayer.create(Game.this, R.raw.gun);
                        mP2.start();
                        if (!mP2.isPlaying()) {
                            mP2.release();
                            try {
                                mP2.prepare();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        bullets--;
                        tv2.setText(String.valueOf(bullets));
                        shot = true;

                    } else {
                        shot = false;
                        mP2 = MediaPlayer.create(Game.this,R.raw.drygun);
                        mP2.start();
                        if (!mP2.isPlaying()) {
                            mP2.release();
                            try {
                                mP2.prepare();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

            }
        });





    }
    public void endGame(){
        Intent intent = new Intent(this,End.class);
        intent.putExtra("win",win);
        startActivity(intent);
        finish();
    }
    public void die()  {
        MediaPlayer mP2;

        mP2 =MediaPlayer.create(Game.this,R.raw.dying);
        mP2.start();
        if(!mP2.isPlaying()){
            mP2.release();
        }
        }
        public void reload(){
            MediaPlayer mP2;

            mP2 =MediaPlayer.create(Game.this,R.raw.reload);
            mP2.start();
            if(!mP2.isPlaying()){
                mP2.release();
            }
        }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float currentX = event.values[0];
        float currentY = event.values[1];
        float currentZ = event.values[2];

        if(notFirst){
            float xDiff = Math.abs(lastX - currentX);
            float yDiff = Math.abs(lastY - currentY);
            float zDiff = Math.abs(lastZ - currentZ);
            float shakeThreshold = 5f;
            if((xDiff > shakeThreshold && yDiff > shakeThreshold) ||
                    (xDiff > shakeThreshold && zDiff > shakeThreshold) ||
                    (yDiff > shakeThreshold && zDiff > shakeThreshold)){
                reload=true;
                bullets=6;
                onPause();
            }

        }
        lastX= currentX;
        lastY = currentY;
        lastZ = currentZ;
        notFirst = true;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(aSA){
            sM.registerListener(this,aS,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(aSA){
            sM.unregisterListener(this);
        }
    }
}
