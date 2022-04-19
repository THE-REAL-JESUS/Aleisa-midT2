package com.example.aleisa_midt2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        DatabaseHelper myDB= new DatabaseHelper(this);
        Button readbtn= (Button) findViewById(R.id.searchBtn);
        Button deletebtn= (Button) findViewById(R.id.deletebtn);
        Button goinsert= (Button) findViewById(R.id.goInsert);
        TextView result= (TextView) findViewById(R.id.resulttt);
        EditText id= (EditText) findViewById(R.id.idinp);
        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=myDB.getListContents();

                if (res.moveToFirst()){
                    result.setText("");
                    do{
                        result.setText(result.getText().toString()+" "+res.getString(0)+" "+res.getString(1)+" "+res.getString(2)+" "+res.getString(3)+"\n");
                    }
                    while(res.moveToNext());
                }
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=myDB.deleteRow(id.getText().toString());
                if(i==0){Toasty.error(getBaseContext(), "Failed deletion",
                        Toast.LENGTH_SHORT, true).show();
                    Log.d("Talal","Failed deletion");

                }else {
                    Toasty.success(getBaseContext(), "Success.", Toast.LENGTH_SHORT,true).show();
                    Log.d("Talal","successful deletion");
                }


            }
        });

        goinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Search.this,Insert.class);
                startActivity(intent);
            }
        });
    }
}