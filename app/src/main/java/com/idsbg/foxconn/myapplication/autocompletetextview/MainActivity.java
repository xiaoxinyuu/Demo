package com.idsbg.foxconn.myapplication.autocompletetextview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.idsbg.foxconn.myapplication.R;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView auto;
    MultiAutoCompleteTextView mauto;
    String[] arr=new String[]{
            "简单爱","七里香","霍元甲","珊瑚海","给我一首歌的时间","搁浅","倒带","龙卷风","等你下课"
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocompletetextview);
        auto=findViewById(R.id.auto);
        mauto=findViewById(R.id.mauto);
        ArrayAdapter<String> a=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,arr);
        auto.setAdapter(a);
        mauto.setAdapter(a);
        mauto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
