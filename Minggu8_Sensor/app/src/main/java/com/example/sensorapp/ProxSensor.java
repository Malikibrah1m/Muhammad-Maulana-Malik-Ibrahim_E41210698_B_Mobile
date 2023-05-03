package com.example.sensorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ProxSensor extends AppCompatActivity  implements SensorEventListener {

    private TextView txtInf;
    private SensorManager sensorManager;
    private Sensor sensorProx;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prox_sensor);

        txtInf = findViewById(R.id.txt_info);
        txtInf.setText("Loading.....");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorProx = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (sensorProx == null){
            txtInf.setText("Sensor tidak tersedia");
        } else {
            sensorManager.registerListener(this, sensorProx, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sensorManager.unregisterListener(this, sensorProx);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();

        switch (sensorType){
            case Sensor.TYPE_PROXIMITY:
                txtInf.setText(getResources().getString(R.string.label_proximity, sensorEvent.values[0]));

                if (sensorEvent.values[0] == 0){
                    mediaPlayer = new MediaPlayer();

                    try {
                        AssetFileDescriptor as = this.getAssets().openFd("objek_mendekat.mp3");
                        mediaPlayer.setDataSource(as.getFileDescriptor(), as.getStartOffset(), as.getLength());
                        as.close();
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        Toast.makeText(this, "Objek Mendekat", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    mediaPlayer = new MediaPlayer();
                    try {
                        AssetFileDescriptor as = this.getAssets().openFd("objek_menjauh.mp3");
                        mediaPlayer.setDataSource(as.getFileDescriptor(), as.getStartOffset(), as.getLength());
                        as.close();
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        Toast.makeText(this, "Objek Menjauh", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}