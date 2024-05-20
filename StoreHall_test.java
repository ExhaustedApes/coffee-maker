package com.example.a0411;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class StoreHall_test extends AppCompatActivity {

    Intent Intent3;
    int weather = -1;
    // Button weather_button;
    private View.OnClickListener m_listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // WeatherTime.java로부터 weather 값을 받아옴.
        Intent3 = getIntent();
        weather = Intent3.getIntExtra("weather", -1);

        Button weather_button = (Button)findViewById(R.id.weather_index);
        weather_button.setText(String.valueOf(weather));


    }






}