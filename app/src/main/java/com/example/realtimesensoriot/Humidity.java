package com.example.realtimesensoriot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.github.lzyzsd.circleprogress.CircleProgress;

public class Humidity extends AppCompatActivity {

    public Firebase myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity);

        CircleProgress circleProgress =findViewById(R.id.circle_progress);

        Button btnBack = findViewById(R.id.btnback);



        TextView Hum = findViewById(R.id.txthum);

        myRef = new Firebase("https://realtimeiot-add91-default-rtdb.firebaseio.com/hum/humVal");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String hum = dataSnapshot.getValue(String.class);

                Hum.setText(hum);

                circleProgress.setProgress((int) Float.parseFloat(hum));
                circleProgress.setMax(200);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Humidity.this, MainActivity.class));
            }
        });
    }
}