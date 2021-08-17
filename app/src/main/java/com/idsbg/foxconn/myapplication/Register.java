package com.idsbg.foxconn.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        final Button bn=findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name=findViewById(R.id.name);
                EditText password=findViewById(R.id.password);
                RadioButton male=findViewById(R.id.male);
                String gender=male.isChecked()?"男":"女";
                Person p=new Person(name.getText().toString(),password.getText().toString(),gender);
                Bundle bundle=new Bundle();
                bundle.putSerializable("person",p);
                Intent intent=new Intent(Register.this,ResultActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
class Person implements Serializable {
    private String name;
    private String password;
    private String gender;

    public Person(String name, String password, String gender) {
        this.name = name;
        this.password = password;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}