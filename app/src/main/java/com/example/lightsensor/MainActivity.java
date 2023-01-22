package com.example.lightsensor;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private TextView lightLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightLevel = (TextView) findViewById(R.id.light_level);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
    }
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float value = event.values[0];
            lightLevel.setText("Razina svijetla iznosi " + value + " lumena");
            int color = 0;
            if (Math.round(value) < 200) {
                color = Color.parseColor("#000000");
                lightLevel.setTextColor(Color.WHITE);
            } else if (Math.round(value) > 200 && Math.round(value) < 400) {
                color = Color.parseColor("#f5a24e");
            } else if (Math.round(value) > 400 && Math.round(value) < 600) {
                color = Color.parseColor("#f5ef4e");
            } else if (Math.round(value) > 600 && Math.round(value) < 800) {
                color = Color.parseColor("#59f54e");
            } else if (Math.round(value) > 800 && Math.round(value) < 1000) {
                color = Color.parseColor("#4e8ef5");
            } else if (Math.round(value) > 1000 && Math.round(value) < 1200) {
                color = Color.parseColor("#f54eef");
            } else {
                color = Color.parseColor("#ffffff");
                lightLevel.setTextColor(Color.BLACK);
            }
            lightLevel.setBackgroundColor(color);
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
}