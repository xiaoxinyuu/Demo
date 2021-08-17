package com.idsbg.foxconn.myapplication.material;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.idsbg.foxconn.myapplication.R;

public class MaterialActivity extends Activity {
    FloatingActionButton fb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.materialxml);
        fb=(FloatingActionButton)findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"出现了",Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
