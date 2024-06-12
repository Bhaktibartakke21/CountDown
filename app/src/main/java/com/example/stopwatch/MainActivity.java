package com.example.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
private Chronometer chromometer;
private long PauseOffset = 0;
private boolean isPlaying = false;
private ToggleButton toggleButton;
private Button reset_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        chromometer = findViewById(R.id.chronometersid);
        toggleButton=  findViewById(R.id.toggle);
        reset_btn = findViewById(R.id.reset_button);
        toggleButton.setText(null);
        toggleButton.setTextOn(null);
        toggleButton.setTextOff(null);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chromometer.setBase(SystemClock.elapsedRealtime()-PauseOffset);
                    chromometer.start();
                    isPlaying =true;

                }
                else {
                    chromometer.stop();
                    PauseOffset = SystemClock.elapsedRealtime()-chromometer.getBase();
                    isPlaying=false;
                }
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying){
                    chromometer.setBase(SystemClock.elapsedRealtime());
                    PauseOffset=0;
                    chromometer.start();;
                    isPlaying =true;
                }
            }
        });
    }
}