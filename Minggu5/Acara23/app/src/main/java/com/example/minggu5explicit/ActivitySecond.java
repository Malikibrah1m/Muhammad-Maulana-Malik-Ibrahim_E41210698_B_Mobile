package com.example.minggu5explicit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivitySecond extends AppCompatActivity {
    TextView txtHello;
    private String nama;
    private String KEY_NAME = "NAMA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txtHello = (TextView) findViewById(R.id.hello);
        Bundle extras = getIntent().getExtras();
        nama = extras.getString(KEY_NAME);
        txtHello.setText("Hello, "+nama+" !");
    }
}