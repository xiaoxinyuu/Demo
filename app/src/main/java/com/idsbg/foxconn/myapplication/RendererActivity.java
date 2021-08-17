package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class RendererActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        MyRenderer renderer = new MyRenderer();
        glSurfaceView.setRenderer(renderer);
        setContentView(glSurfaceView);
    }
}
