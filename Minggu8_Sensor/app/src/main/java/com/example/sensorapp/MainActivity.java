package com.example.sensorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView allList, lightSens, proxSens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allList = findViewById(R.id.list);
        lightSens = findViewById(R.id.light);
        proxSens = findViewById(R.id.prox);
        allList.setOnClickListener(this);
        lightSens.setOnClickListener(this);
        proxSens.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.list:
                Intent intentList = new Intent(this,AllSensor.class);
                startActivity(intentList);
                break;
            case R.id.light:
                Intent intentLight = new Intent(this, LightSensor.class);
                startActivity(intentLight);
                break;
            case R.id.prox:
                Intent intentProx = new Intent(this, ProxSensor.class);
                startActivity(intentProx);
                break;
        }
    }
}