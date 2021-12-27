package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button, history;
    private TextView  sky, desc, tempe, pres, wind;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.EditText);
        button = findViewById(R.id.Button);
        sky = findViewById(R.id.sky);
        desc = findViewById(R.id.desc);
        tempe = findViewById(R.id.temp);
        pres = findViewById(R.id.press);
        wind = findViewById(R.id.wind);
        history = findViewById(R.id.History);

        dbHandler = new DBHandler(MainActivity.this);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, History.class);
                startActivity(i);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "City is empty", Toast.LENGTH_SHORT).show();
                }else{
                    getTemp();
                }
            }
        });
    }

    private void getTemp() {
        
        String tempURL = "https://api.openweathermap.org/data/2.5/weather?q="+editText.getText().toString()+"&units=metric&appid=94b49d8deaf4b9d1a950faf3569cf7ff";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, tempURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject ob  = (JSONObject) response.getJSONArray("weather").get(0);
                    JSONObject ob1 = response.getJSONObject("main");
                    JSONObject ob2 = response.getJSONObject("wind");

                    sky.setText(ob.get("main").toString());
                    desc.setText(ob.get("description").toString());
                    tempe.setText(ob1.get("temp").toString()+" °C");
                    pres.setText(ob1.get("pressure").toString()+" hPa");
                    wind.setText(ob2.get("speed").toString()+" m/s");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss", Locale.getDefault());
                    String currentDateandTime = sdf.format(new Date());

                    dbHandler.addCity(editText.getText().toString(),ob.get("main").toString(),ob.get("description").toString(),ob1.get("temp").toString()+" °C"
                            ,ob1.get("pressure").toString()+" hPa",ob2.get("speed").toString()+" m/s",currentDateandTime);
                }catch (JSONException e){
                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int er = error.networkResponse.statusCode;
                if(er == 404){
                    Toast.makeText(MainActivity.this, "City not exist", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        queue.add(request);
    }
}