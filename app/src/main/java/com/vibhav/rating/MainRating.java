package com.vibhav.rating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainRating extends AppCompatActivity {
    TextView rating;
    SeekBar ratingSeek;
    int lowerLimit,upperLimit, rat;
    Button submit, database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rating);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        lowerLimit = extras.getInt("lower");
        upperLimit = extras.getInt("upper");
        rating = findViewById(R.id.rating);
        ratingSeek = findViewById(R.id.ratingSeekBar);
        submit = findViewById(R.id.submit);
        database = findViewById(R.id.database);
        ratingSeek.setMax(upperLimit-lowerLimit+1);
        ratingSeek.setProgress(0);
        rat = lowerLimit;
        rating.setText(String.valueOf(rat));
        ratingSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ratingSeek.setProgress(i);
                rat = i+lowerLimit;
                String s = (i+lowerLimit)+"";
                rating.setText(s);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
