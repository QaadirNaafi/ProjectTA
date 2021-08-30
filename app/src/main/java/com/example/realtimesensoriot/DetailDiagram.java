package com.example.realtimesensoriot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.shinelw.library.ColorArcProgressBar;

public class DetailDiagram extends AppCompatActivity {

    public Firebase mRef1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_diagram);

        mRef1 = new Firebase("https://realtimeiot-add91-default-rtdb.firebaseio.com/temp/tempVal");

        ColorArcProgressBar progressBar = findViewById(R.id.bar1);

        Button btnBack = findViewById(R.id.btnback);

        TextView Suhu = findViewById(R.id.txtsuhu);

        mRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String temp = dataSnapshot.getValue(String.class);

                Suhu.setText(temp);
                progressBar.setCurrentValues((int) Float.parseFloat(temp));
//                progressBar.setCurrentValues(50);
//                progressBar.setMaxValues(70);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailDiagram.this, MainActivity.class));
            }
        });

    }

}