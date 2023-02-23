package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void LV(View view){
        Intent intent = new Intent(MainActivity.this, LV.class);
        startActivity(intent);
    }

    public void LisVi(View view) {
        Intent pindah = new Intent(MainActivity.this, LV.class);
        startActivity(pindah);
    }
}