package com.example.extenalpratice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity2 extends AppCompatActivity {
    EditText txtname,txtcost,txtid;
    DatePicker txtdate;
    CheckBox txtDiabities,txtBp;
    RadioButton radioYesNo;
    RadioGroup radioGroup;
    Button btnInsert,btnUpdate,btnDelete,btnSerchData,btnReset,btnShow;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtid = (EditText) findViewById(R.id.txtid);
        txtname = (EditText) findViewById(R.id.txtname);
        txtcost = (EditText) findViewById(R.id.txtcost);
        txtdate = (DatePicker) findViewById(R.id.txtdate);
        txtDiabities = (CheckBox) findViewById(R.id.txtDiabities);
        txtBp = (CheckBox) findViewById(R.id.txtBp);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnInsert =  (Button) findViewById(R.id.btnInsert);
        btnUpdate =  (Button) findViewById(R.id.btnUpdate);
        btnDelete =  (Button) findViewById(R.id.btnDelete);
        btnSerchData =  (Button) findViewById(R.id.btnSerchData);
        btnReset =  (Button) findViewById(R.id.btnReset);
        btnShow =  (Button) findViewById(R.id.btnShow);

        DB = new DbHelper(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtname.getText().toString();
                int cost = Integer.parseInt(txtcost.getText().toString());
                String date = getCurrentDate();
                String val="";
                if(txtDiabities.isChecked()){
                    val = "Diabities,";
                }
                if(txtBp.isChecked()){
                    val += "Bp,";
                }
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioYesNo = (RadioButton) findViewById(selectedId);
                String isMedication="";
                if(selectedId==-1){
                    isMedication="Yes";
                }
                else{
                    isMedication="No";
                }
                boolean result = DB.insertData(name,val,isMedication,date,cost);
                if(result)
                {
                    Toast.makeText(MainActivity2.this,"Insert",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity2.this,"Not Insert",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(txtid.getText().toString()) ;
                String name = txtname.getText().toString();
                int cost = Integer.parseInt(txtcost.getText().toString());
                String date = getCurrentDate();
                String val="";
                if(txtDiabities.isChecked()){
                    val = "Diabities,";
                }
                if(txtBp.isChecked()){
                    val += "Bp,";
                }
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioYesNo = (RadioButton) findViewById(selectedId);
                String isMedication="";
                if(selectedId==-1){
                    isMedication="Yes";
                }
                else{
                    isMedication="No";
                }
                boolean result = DB.updateData(id,name,val,isMedication,date,cost);
                if(result)
                {
                    Toast.makeText(MainActivity2.this,"Update",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity2.this,"Not Update",Toast.LENGTH_LONG).show();
                }

            }

        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtid.setText("");
                txtname.setText("");
                txtDiabities.setChecked(false);
                txtBp.setChecked(false);
                txtcost.setText("");
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getData();
                if(res.getCount()==0)
                {
                    Toast.makeText(MainActivity2.this,"Empty",Toast.LENGTH_LONG).show();
                }
                else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Id : " + res.getString(0) + "\n\n");
                        buffer.append("Name : " + res.getString(1) + "\n\n");
                        buffer.append("disease : " + res.getString(2) + "\n\n");
                        buffer.append("isMedication : " + res.getString(3) + "\n\n");
                        buffer.append("arrivalDate : " + res.getString(4) + "\n\n");
                        buffer.append("cost : " + res.getString(5) + "\n\n");

                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setCancelable(true);
                    builder.setMessage(buffer.toString());
                    builder.setTitle("Student");
                    builder.show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(txtid.getText().toString());
                boolean result = DB.deleteData(id);
                if(result)
                {
                    Toast.makeText(MainActivity2.this,"Delete",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity2.this,"Not Delete",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();;
        builder.append((txtdate.getMonth() + 1)+"/");//month is 0 based
        builder.append(txtdate.getDayOfMonth()+"/");
        builder.append(txtdate.getYear());
        return builder.toString();
    }
}