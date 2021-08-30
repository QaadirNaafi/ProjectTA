package com.example.realtimesensoriot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ekn.gruzer.gaugelibrary.FullGauge;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class Gas extends AppCompatActivity {

    public Firebase mRef2;

    private static final String CHANNEL_ID = "RealtimeSensorIOT";
    private static final int NOTIFICATION_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

//        DonutProgress donutProgress = findViewById(R.id.donut_progress);

//        CircularProgressBar circularProgressBar = findViewById(R.id.progress_circular);

        FullGauge fullGauge = findViewById(R.id.full_gauge);

        Button btnBack = findViewById(R.id.btnback);

        TextView Gas = findViewById(R.id.txtgas);

        mRef2 = new Firebase("https://realtimeiot-add91-default-rtdb.firebaseio.com/gas/gasVal");

        mRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String gas = dataSnapshot.getValue(String.class);

                Gas.setText(gas);
                fullGauge.setValue(Float.parseFloat(gas));
                fullGauge.setMaxValue(900);
//                circularProgressBar.setProgress(Integer.parseInt(gas));

                Intent intent = new Intent(Gas.this, Gas.class);
                PendingIntent pi = PendingIntent.getActivity(Gas.this, 0, intent, 0);

                if (350 < Integer.parseInt(gas)) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Gas.this, CHANNEL_ID);
                    builder.setSmallIcon(R.drawable.ic_baseline_home_24);
                    builder.setLargeIcon(BitmapFactory.decodeResource(Gas.this.getResources(), R.drawable.ic_baseline_home_24));
                    builder.setContentTitle("Gas Sensor");
                    builder.setContentText("Kebocoran Gas Terdeteksi");
                    builder.setContentIntent(pi);
                    builder.setAutoCancel(true);
                    builder.setDefaults(Notification.DEFAULT_ALL);
                    builder.setPriority(NotificationCompat.PRIORITY_HIGH);
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(Gas.this);
                    notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gas.this, MainActivity.class));
            }
        });
    }
}