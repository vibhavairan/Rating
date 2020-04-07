package com.vibhav.rating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView lower, upper;
    SeekBar lowerSeek, upperSeek;
    int lowerLimit,upperLimit=9;
    Button range, database;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lower = findViewById(R.id.lower);
        upper = findViewById(R.id.upper);
        lowerSeek = findViewById(R.id.lowerSeekBar);
        upperSeek = findViewById(R.id.upperSeekBar);
        range = findViewById(R.id.range);
        database = findViewById(R.id.database);
        lowerSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                lowerSeek.setProgress(i);
                lowerLimit = i;
                String s = i+"";
                lower.setText(s);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                upperSeek.setMax(8-lowerLimit);
                upperSeek.setProgress(0);
                String s = (lowerLimit+1)+"";
                upper.setText(s);
                String m = "Rating <"+ lowerLimit + " - "+ upperLimit +">";
                range.setText(m);
                upperLimit = lowerLimit+1;
                flag = true;
            }
        });

        upperSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                upperSeek.setProgress(i);
                upperLimit = i+lowerLimit+1;
                String s = (i+lowerLimit+1)+"";
                upper.setText(s);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(flag) {
                    String s = "Rating <"+ lowerLimit + " - "+ upperLimit +">";
                    range.setText(s);
                }
            }
        });

        range.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lowerLimit==8) {
                    flag = true;
                    upperLimit = 9;
                }
                if(flag&&lowerLimit<upperLimit) {
                    Intent intent = new Intent(MainActivity.this, MainRating.class);
                    Bundle extras = new Bundle();
                    extras.putInt("lower",lowerLimit);
                    extras.putInt("upper",upperLimit);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this,"Choose both limits!",Toast.LENGTH_SHORT).show();
            }
        });

        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Fetching data from Firebase", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, ShowRatings.class));
            }
        });
    }
}
