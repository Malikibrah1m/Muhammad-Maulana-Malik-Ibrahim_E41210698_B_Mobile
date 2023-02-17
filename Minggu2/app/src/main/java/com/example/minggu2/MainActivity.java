package com.example.minggu2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void TableLayout(View view) {
        Intent intent = new Intent(MainActivity.this, TableLayout.class);
        startActivity(intent);
    }

    public void FrameLayout(View view) {
        Intent intent = new Intent(MainActivity.this, FrameLayout.class);
        startActivity(intent);
    }

    public void MaterialDesign(View view) {
        Intent intent = new Intent(MainActivity.this, MaterialDesign.class);
        startActivity(intent);
    }

    public void RelativeLayout(View view) {
        Intent intent = new Intent(MainActivity.this, RelativeLayout.class);
        startActivity(intent);
    }

    public void ConstraintLayout(View view) {
        Intent intent = new Intent(MainActivity.this, ContrainsLayout.class);
        startActivity(intent);
    }

    public void LinearLayout(View view) {
        Intent intent = new Intent(MainActivity.this, LinearLayout.class);
        startActivity(intent);
    }

    public void HorizontalScroll(View view) {
        Intent intent = new Intent(MainActivity.this, HorizontalScroll.class);
        startActivity(intent);
    }

    public void VerticalScroll(View view) {
        Intent intent = new Intent(MainActivity.this, VerticalScroll.class);
        startActivity(intent);
    }
}