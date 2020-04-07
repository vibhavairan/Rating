package com.vibhav.rating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowRatings extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<RatingModel> list;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ratings);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<RatingModel>();

        reference = FirebaseDatabase.getInstance().getReference("Ratings");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    RatingModel r = dataSnapshot1.getValue(RatingModel.class);
                    list.add(r);
                }
                adapter = new MyAdapter(ShowRatings.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ShowRatings.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
