package com.example.projekadalahini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();

        Intent in = getIntent();
        Bundle bundle = in.getExtras();

        String emailSimpan = (String) bundle.get("email");

        View tombol = findViewById(R.id.ellipse_4);
        RelativeLayout tombol2 = findViewById(R.id.tombolPojokKiri);
        tombol.setOnClickListener(e->{
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        });
        tombol2.setOnClickListener(e->{
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        });

        View tombol3 = findViewById(R.id.ellipse_6);
        RelativeLayout tombol4 = findViewById(R.id.tombolPojokKananBawah);
        tombol3.setOnClickListener(e->{
            Intent intent = new Intent(this, BonusActivity.class);
            intent.putExtra("email", emailSimpan);
            startActivity(intent);
        });
//        tombol4.setOnClickListener(e->{
//            Intent intent = new Intent(this, BonusActivity.class);
//            intent.putExtra("email", emailSimpan);
//            startActivity(intent);
//            startActivity(intent);
//        });

        View tombol5 = findViewById(R.id.ellipse_4_ek1);
        RelativeLayout tombol6 = findViewById(R.id.tombolTengahBawah);
        tombol5.setOnClickListener(e->{
            Intent intent = new Intent(this, ForumActivity.class);
            startActivity(intent);
        });
        tombol6.setOnClickListener(e->{
            Intent intent = new Intent(this, ForumActivity.class);
            startActivity(intent);
        });

        View tombol7 = findViewById(R.id.ellipse_5);
        RelativeLayout tombol8 = findViewById(R.id.tomboPojokKananAtas);
        tombol7.setOnClickListener(e->{
            Intent intent = new Intent(this, HistoryActivity.class);
            intent.putExtra("email", emailSimpan);
            startActivity(intent);
        });
//        tombol8.setOnClickListener(e->{
//            Intent intent = new Intent(this, HistoryActivity.class);
//            intent.putExtra("email", emailSimpan);
//            startActivity(intent);
//        });

        View tombol9 = findViewById(R.id.ellipse_3);
        RelativeLayout tombol10 = findViewById(R.id.tombolPojokKiriAtas);
        tombol9.setOnClickListener(e->{
            Intent intent = new Intent(this, ScheduleListActivity.class);
            startActivity(intent);
        });
        tombol10.setOnClickListener(e->{
            Intent intent = new Intent(this, ScheduleListActivity.class);
            startActivity(intent);
        });

        View tombol11 = findViewById(R.id.ellipse_55);
        RelativeLayout tombol12 = findViewById(R.id.tombolTengahAtas);
        tombol11.setOnClickListener(e->{
            Intent intent = new Intent(this, RouteActivity.class);
            startActivity(intent);
        });
        tombol12.setOnClickListener(e->{

            Intent intent = new Intent(this, RouteActivity.class);
            intent.putExtra("email", emailSimpan);
            startActivity(intent);
        });

        View tombol_bus = findViewById(R.id.rectangle_177);
        tombol_bus.setOnClickListener(e->{
            Intent intent = new Intent(this, BusScheduleActivity.class);
            startActivity(intent);
        });

        View tombol_train = findViewById(R.id.rectangle_17);
        tombol_train.setOnClickListener(e->{
            Intent intent = new Intent(this, TrainScheduleActivity.class);
            startActivity(intent);
        });

        EditText tombolRoute = findViewById(R.id.rectangle_1);
        tombolRoute.setOnClickListener(e->{
            Intent intent = new Intent(this, RouteActivity.class);
            startActivity(intent);
        });
    }
}