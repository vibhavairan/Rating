package com.vibhav.rating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.core.Tag;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
        ratingSeek.setMax(upperLimit-lowerLimit);
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("rating",rat);
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                hashMap.put("date",date);
                hashMap.put("time",currentTime);
                reference.child("Ratings").push().setValue(hashMap);
                Toast.makeText(MainRating.this, "Rating added to database", Toast.LENGTH_SHORT).show();
            }
        });

        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainRating.this, "Fetching data from Firebase", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainRating.this, ShowRatings.class));
            }
        });
    }
}
