package com.idsbg.foxconn.myapplication.zhinanzhen;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.idsbg.foxconn.myapplication.R;

public class DegreeActivity extends Activity implements SensorEventListener {
    SensorManager sensorManager;
    ImageView imageView;
    //记录指南针旋转的角度
    float currentDegree=0f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.degree);
        imageView = (ImageView) findViewById(R.id.zhinanzhen);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        //取消注册
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //获取触发event的传感器
        int sensorType = event.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_ORIENTATION:
                //获取绕Z轴旋转的速度
                float degree = event.values[0];
                //创建旋转动画
                RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f);
                //设置动画的持续时间
                ra.setDuration(200);
                //运行动画
                imageView.startAnimation(ra);
                currentDegree = -degree;
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
