package com.example.projekadalahini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class ScheduleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);

        getSupportActionBar().hide();

        View tombol = findViewById(R.id.rectangle_32);
        RelativeLayout tombol2 = findViewById(R.id.tombolBus);
        tombol.setOnClickListener(e->{
            Intent intent = new Intent(this, BusScheduleActivity.class);
            startActivity(intent);
        });
        tombol2.setOnClickListener(e->{
            Intent intent = new Intent(this, BusScheduleActivity.class);
            startActivity(intent);
        });

        View tombol3 = findViewById(R.id.rectangle_32_ek1);
        RelativeLayout tombol4 = findViewById(R.id.tombolKereta);
        tombol3.setOnClickListener(e->{
            Intent intent = new Intent(this, TrainScheduleActivity.class);
            startActivity(intent);
        });
        tombol4.setOnClickListener(e->{
            Intent intent = new Intent(this, TrainScheduleActivity.class);
            startActivity(intent);
        });
    }
}