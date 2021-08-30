package com.example.realtimesensoriot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public TextView nilai3;
    public Firebase mRef3;

    private static final String CHANNEL_ID = "RealtimeSensorIOT";
    private static final int NOTIFICATION_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button LED = findViewById(R.id.btnled);
        Button About = findViewById(R.id.btnabout);
        ImageButton btn_temp = findViewById(R.id.btn_temp);
        ImageButton btn_hum = findViewById(R.id.btn_hum);
        ImageButton btn_gas = findViewById(R.id.btn_gas);


        LED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ControlLED.class));
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, About.class));
            }
        });

        btn_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DetailDiagram.class));
            }
        });
        btn_hum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Humidity.class));
            }
        });
        btn_gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Gas.class));
            }
        });

        nilai3 = (TextView)findViewById(R.id.nilai3);

        mRef3 = new Firebase("https://realtimeiot-add91-default-rtdb.firebaseio.com/api/apiVal");


        // Flame Sensor
        mRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String api = dataSnapshot.getValue(String.class);

                if("1".equals(api)){
                    nilai3.setText("OFF");

                }else{
                    nilai3.setText("ON");

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                    builder.setSmallIcon(R.drawable.ic_baseline_home_24);
                    builder.setLargeIcon(BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.ic_baseline_home_24));
                    builder.setContentTitle("Flame Sensor");
                    builder.setContentText("Api Terdeteksi");
                    builder.setAutoCancel(true);
                    builder.setDefaults(Notification.DEFAULT_ALL);
                    builder.setPriority(NotificationCompat.PRIORITY_HIGH);
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
                    notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}