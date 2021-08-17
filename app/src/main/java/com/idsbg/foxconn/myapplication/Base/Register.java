package com.idsbg.foxconn.myapplication.Base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import com.idsbg.foxconn.myapplication.R;

import org.jetbrains.annotations.Nullable;

public class Register extends Activity {
    private EditText username, password, an_password, codee;
    ImageView imageView=null;
    LinearLayout linearLayout1, linearLayout2;
    private Button reg;
    Code code=new Code();
    private MySqlite mySqlite = new MySqlite(this, "MyDatabase.db3", 1);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        an_password = (EditText) findViewById(R.id.an_password);
        codee = (EditText) findViewById(R.id.code);
        imageView = (ImageView) findViewById(R.id.codeBitmap);
        imageView.setImageBitmap(code.createBitmap());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(code.createBitmap());
            }
        });
        reg = (Button) findViewById(R.id.reg);

        System.out.println(code.getCode());


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = username.getText().toString();
                String pw = password.getText().toString();

                System.out.println(codee.getText().toString());

                if (!code.getCode().equalsIgnoreCase(codee.getText().toString())) {
                    Toast.makeText(Register.this, "注册失败,验证码错误", Toast.LENGTH_SHORT).show();
                }
                else if(!password.getText().toString().equals(an_password.getText().toString())){
                    Toast.makeText(Register.this, "注册失败,两次密码不一致", Toast.LENGTH_SHORT).show();
                }
                else {
                    long id = mySqlite.insertData(mySqlite.getReadableDatabase(), un, pw);
                    Toast.makeText(Register.this, id==-1?"注册失败":"注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Log.class);
                    startActivity(intent);
                }
            }
        });
    }
}
