package com.example.aleisa_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Insert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        DatabaseHelper myDB= new DatabaseHelper(this);
        EditText ID= (EditText) findViewById(R.id.ID);
        EditText name= (EditText) findViewById(R.id.name);
        EditText surname= (EditText) findViewById(R.id.surname);
        EditText nationalID= (EditText) findViewById(R.id.nationalID);
        Button gotofirstact=(Button) findViewById(R.id.goFirstAct);
        Button insertBtn=(Button) findViewById(R.id.insertButton);
        Button searchAct=(Button) findViewById(R.id.gotosearch);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myDB.addData(ID.getText().toString(),name.getText().toString(),surname.getText().toString(),nationalID.getText().toString())){
                    Toasty.success(getBaseContext(), "Succesful Insert", Toast.LENGTH_SHORT,true).show();
                    Log.d("Talal","Succesful insertion");
                }else{
                    Toasty.error(getBaseContext(), "Failed deletion",
                            Toast.LENGTH_SHORT, true).show();
                    Log.d("Talal","Failed insertion");
                }

            }
        });



        gotofirstact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Insert.this,MainActivity.class);
                startActivity(intent);
            }
        });
        searchAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Insert.this,Search.class);
                startActivity(intent);
            }
        });



    }
}