package com.example.sensorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.IOException;
import java.util.logging.Logger;

public class LightSensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor ligthSensor;
    private TextView txtBrightnessInfo;
    private MediaPlayer mediaPlayer;
    private GraphView mGraph;
    private LineGraphSeries<DataPoint> mSeriesLight;
    private double graphLastAccelXValues = 5d;

    public static boolean isPlaying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);
        txtBrightnessInfo = findViewById(R.id.txt_bright_info);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ligthSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (ligthSensor == null){
            txtBrightnessInfo.setText("Sensor Tidak Tersedia");
        } else {
            sensorManager.registerListener(this, ligthSensor, sensorManager.SENSOR_DELAY_NORMAL);
        }
        mGraph = initGraph(R.id.graph_light, "Sensor Of Light");
        mSeriesLight = initSeries(Color.RED, "Lux");
        mGraph.addSeries(mSeriesLight);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();

        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                txtBrightnessInfo.setText(getResources().getString(R.string.label_brightness, sensorEvent.values[0]));

                graphLastAccelXValues += 0.15d;
                mSeriesLight.appendData(new DataPoint(graphLastAccelXValues, sensorEvent.values[0]),true, 33);

                if (sensorEvent.values[0] == 0) {
                    mediaPlayer = new MediaPlayer();

                    try {
                        if (!isPlaying) {
                            AssetFileDescriptor as = this.getAssets().openFd("cahaya_gelap.mp3");
                            mediaPlayer.setDataSource(as.getFileDescriptor(), as.getStartOffset(), as.getLength());
                            as.close();
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                            isPlaying = true;

                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            isPlaying = false;
                                        }
                                    }, mediaPlayer.getDuration());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (sensorEvent.values[0] > 80) {
                    mediaPlayer = new MediaPlayer();

                    try {
                        if (!isPlaying) {
                            AssetFileDescriptor as = this.getAssets().openFd("cahaya_terang.mp3");
                            mediaPlayer.setDataSource(as.getFileDescriptor(), as.getStartOffset(), as.getLength());
                            as.close();
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                            isPlaying = true;

                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            isPlaying = false;
                                        }
                                    }, mediaPlayer.getDuration());
                        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sensorManager.unregisterListener(this, ligthSensor);
    }

    public GraphView initGraph(int id, String title) {
        GraphView graph = (GraphView) findViewById(id);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(5);
        graph.getGridLabelRenderer().setLabelVerticalWidth(100);
        graph.setTitle(title);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        return graph;
    }
    public LineGraphSeries<DataPoint> initSeries(int color, String title) {
        LineGraphSeries<DataPoint> series;
        series = new LineGraphSeries<>();
        series.setDrawDataPoints(true);
        series.setDrawBackground(true);
        series.setColor(color);
        series.setTitle(title);
        series.setBackgroundColor(Color.argb(100, 204, 119, 119));
        return series;
    }
}
