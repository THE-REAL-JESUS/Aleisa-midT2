package com.example.aleisa_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    TextView temperature;
    TextView humidity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button dateBtn= (Button) findViewById(R.id.datebtn);
        Button gotoact1= (Button) findViewById(R.id.gottoact1);
        TextView dateResult = (TextView) findViewById(R.id.dateResult);
         temperature = (TextView) findViewById(R.id.weather);
         humidity = (TextView) findViewById(R.id.humidity);
         Log.d("Talal","Succesful initialization of objeects from xml");
        String url ="http://api.openweathermap.org/data/2.5/weather?q=london&appid=9c2c754ed9190deeb0086f19b40aa026&units=metric";
        weather(url);
        Calendar c = Calendar.getInstance();
        DateFormat dateFormat = DateFormat.getDateInstance();
        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                c.set(Calendar.YEAR, i);
                c.set(Calendar.MONTH, i1);
                c.set(Calendar.DAY_OF_MONTH, i2);
                dateResult.setText("Date picked is "+dateFormat.format(c.getTime()));
            }
        } ;
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,d,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        gotoact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,Insert.class);
                startActivity(intent);
            }
        });



    }
    public void weather(String url){
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.d("Talal",response.toString());
                    JSONObject jsonMain=response.getJSONObject("main");
                    JSONObject jsonSys = response.getJSONObject("sys");
                    JSONArray jsonWeather = response.getJSONArray("weather");
                    JSONObject jsonWeatherObject = jsonWeather.getJSONObject(0);
                    String weather = jsonWeatherObject.getString("main");
                    temperature.setText(jsonMain.getDouble("temp")+"°C");
                    humidity.setText("Humidity: "+ jsonMain.getDouble("humidity")+"%");


                }

                catch(Exception e){

                }


            }
        }, new Response.ErrorListener() {
            @Override     public void onErrorResponse(VolleyError error) {
                Log.d("Talal",error.toString());

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);


    }
}