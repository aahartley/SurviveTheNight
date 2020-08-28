package com.example.survivethenight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class End extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
         TextView tV = findViewById(R.id.textView2);
         tV.setText(R.string.youLose);
        final  Button bt = findViewById(R.id.button2);

        bt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startOver();
                return true;
            }
        });

    }

    public void startOver(){
        Intent intent = new Intent(this,Game.class);
        startActivity(intent);
        finish();
    }
}
