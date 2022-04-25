package com.example.extenalpratice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    ListView ls;
    DbHelper DB;
    Button btncrud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ls = (ListView) findViewById(R.id.myList);
        DB = new DbHelper(this);
        btncrud = (Button) findViewById(R.id.btncrud);
        List<String> lls=new ArrayList<>();

        Cursor c;
        c = DB.getData();
        if(c.getCount()==0)
        {
            Toast.makeText(MainActivity4.this,"Empty",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(c.moveToNext())
            {
                lls.add(c.getString(1));
            }
            ArrayAdapter<String> adp=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lls);
            ls.setAdapter(adp);
        }

        btncrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity4.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String myData = adapterView.getItemAtPosition(i).toString();
                Intent intent1 = new Intent(MainActivity4.this,MainActivity3.class);
                intent1.putExtra("Name",myData);
                startActivity(intent1);
            }
        });
    }
}