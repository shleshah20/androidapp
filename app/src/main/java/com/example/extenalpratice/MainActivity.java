package com.example.extenalpratice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("mypereference",MODE_PRIVATE);
                SharedPreferences.Editor esp = sp.edit();
                esp.putString("username", username.getText().toString());
                esp.putString("password",password.getText().toString());
                esp.apply();

                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this, "Hello Doctor", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,MainActivity4.class);
                    startActivity(intent);

                }
                else{
                    Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                    intent.putExtra("Name",username.getText().toString());
                    startActivity(intent);
                }



            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sp = getSharedPreferences("mypereference",MODE_PRIVATE);

        String s1 = sp.getString("username","");
        String s2 = sp.getString("password","");

        username.setText(s1);
        password.setText(s2);
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences sp = getSharedPreferences("mypereference",MODE_PRIVATE);
        SharedPreferences.Editor esp = sp.edit();

        esp.putString("username",username.getText().toString());
        esp.putString("password",password.getText().toString());
        esp.apply();
    }
}