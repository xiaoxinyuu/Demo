package com.idsbg.foxconn.myapplication.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.idsbg.foxconn.myapplication.R;

public class SensorActivity extends Activity implements SensorEventListener {
    SensorManager sensorManager;
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor);
        editText = findViewById(R.id.sensor);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //为系统的加速度传感器注册监听器
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        //取消注册
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    //当传感器的值改变是回调该方法
    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        StringBuilder sb = new StringBuilder();
        sb.append("X方向上的速度");
        sb.append(values[0]);
        sb.append("\nY方向上的速度");
        sb.append(values[1]);
        sb.append("\nZ方向上的速度");
        sb.append(values[2]);
        editText.setText(sb.toString());
    }

    //传感度精度改变时回调该方法
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
