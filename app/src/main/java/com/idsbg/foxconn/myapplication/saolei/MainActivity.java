package com.idsbg.foxconn.myapplication.saolei;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import androidx.annotation.Nullable;

import com.idsbg.foxconn.myapplication.R;

public class MainActivity extends Activity {
    GridView gridView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);
        gridView=(GridView) findViewById(R.id.grid);
        gridView.setBackgroundResource(R.drawable.huise);
    }
}
