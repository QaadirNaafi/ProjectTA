package com.example.realtimesensoriot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class ControlLED extends AppCompatActivity {

    public Firebase myRef;
    public Firebase myRef1;
    public Firebase myRef2;
    public Firebase myRef4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_led);

        myRef = new Firebase("https://realtimeiot-add91-default-rtdb.firebaseio.com/LED1/led1");
        myRef1 = new Firebase("https://realtimeiot-add91-default-rtdb.firebaseio.com/LED2/led2");
        myRef2 = new Firebase("https://realtimeiot-add91-default-rtdb.firebaseio.com/LED3/led3");
        myRef4 = new Firebase("https://realtimeiot-add91-default-rtdb.firebaseio.com/PINTU/pintu");

        TextView status1 = findViewById(R.id.status1);
        TextView status2 = findViewById(R.id.status2);
        TextView status3 = findViewById(R.id.status3);
        TextView status5 = findViewById(R.id.status5);

        Button btnBack = findViewById(R.id.btnback);

        Button btnon1 = findViewById(R.id.btnon1);
        Button btnoff1 = findViewById(R.id.btnoff1);
        Button btnon2 = findViewById(R.id.btnon2);
        Button btnoff2 = findViewById(R.id.btnoff2);
        Button btnon3 = findViewById(R.id.btnon3);
        Button btnoff3 = findViewById(R.id.btnoff3);
        Button btnon5 = findViewById(R.id.btnon5);
        Button btnoff5 = findViewById(R.id.btnoff5);

        //Read data status 1 dari Firebase
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String data = dataSnapshot.getValue().toString();
                    status1.setText(data);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //Read data status 2 dari Firebase
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String data = dataSnapshot.getValue().toString();
                    status2.setText(data);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //Read data status 3 dari Firebase
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String data = dataSnapshot.getValue().toString();
                    status3.setText(data);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Read data status 5 dari Firebase
        myRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String data = dataSnapshot.getValue().toString();
                    status5.setText(data);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        // Button LED 1
        btnon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status1.setText("ON");
                myRef.setValue(status1.getText().toString());
            }
        });
        btnoff1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status1.setText("OFF");
                myRef.setValue(status1.getText().toString());
            }
        });
        //Button LED 2
        btnon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status2.setText("ON");
                myRef1.setValue(status2.getText().toString());
            }
        });
        btnoff2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status2.setText("OFF");
                myRef1.setValue(status2.getText().toString());
            }
        });
        //Button LED 3
        btnon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status3.setText("ON");
                myRef2.setValue(status3.getText().toString());
            }
        });
        btnoff3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status3.setText("OFF");
                myRef2.setValue(status3.getText().toString());
            }
        });
        //Button Pintu Gerbang
        btnon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status5.setText("1");
                myRef4.setValue(status5.getText().toString());
            }
        });
        btnoff5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status5.setText("0");
                myRef4.setValue(status5.getText().toString());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ControlLED.this, MainActivity.class));
            }
        });
    }
}