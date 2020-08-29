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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity implements SensorEventListener {
    public int bullets =2;
    private boolean shot = false;
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
    MediaPlayer mP2;

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
        final TextView tv2 = findViewById(R.id.time3);

        new CountDownTimer(20000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                tV.setText((String.valueOf(counter)));
                mP2 = MediaPlayer.create(Game.this, R.raw.gun);

                counter++;

                if(reload){
                    mP2.start();
                    reload = false;
                }
                if(counter ==5){
                    mP2.start();
                    shot = false;
                }
                    if(counter ==6 && !shot) {
                        mP.stop();
                        cancel();
                        endGame();
                    }
                    if(counter == 10) {
                        shot = false;
                        mP2.start();
                    }
                         if(counter ==12 && !shot) {
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
                mP2 = MediaPlayer.create(Game.this,R.raw.gun);
                if(bullets >0) {
                    mP2.start();
                    bullets --;
                    tv2.setText(String.valueOf(bullets));
                    shot=true;

                }
                else
                    shot = false;

            }
        });





    }
    public void endGame(){
        Intent intent = new Intent(this,End.class);
        startActivity(intent);
        finish();
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
                bullets=6;
                reload=true;

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
