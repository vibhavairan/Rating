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
    Button range;
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
                flag = true;
                String s = "Rating <"+ lowerLimit + " - "+ upperLimit +">";
                range.setText(s);
            }
        });

        range.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lowerLimit==8) {
                    flag = true;
                    upperLimit = 9;
                }
                if(flag) {
                    Toast.makeText(MainActivity.this,lowerLimit+" - "+upperLimit,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainRating.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this,"Choose both limits!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
