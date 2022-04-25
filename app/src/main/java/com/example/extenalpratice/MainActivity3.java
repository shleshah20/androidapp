package com.example.extenalpratice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    TextView txt;
    DbHelper DB;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        txt = findViewById(R.id.Display);

        DB = new DbHelper(this);
        Intent intent = getIntent();
        String val = intent.getStringExtra("Name");
        Cursor c = DB.searchDataName(val);
        String op ="";
        if(c.getCount()==0)
        {
            op+= "No Record Found";
        }
        else
        {
            while(c.moveToNext())
            {
                op += c.getString(0) + c.getString(1) + c.getString(2) + c.getString(3) + c.getString(4) + c.getString(5) + "\n";
            }

        }

        txt.setText(op);




    }
}