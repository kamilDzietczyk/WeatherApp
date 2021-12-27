package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class History extends AppCompatActivity {
    DBHandler dbHandler;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        dbHandler = new DBHandler(History.this);
        tv = findViewById(R.id.val);


            for(int i=0;i<=dbHandler.readCourses().size()-1;i++){
                tv.append(
                        dbHandler.readCourses().get(i).getId()+" " +
                        dbHandler.readCourses().get(i).getCITY()+" " +
                        dbHandler.readCourses().get(i).getTemp()+" " +
                        dbHandler.readCourses().get(i).getSearchTime()+"\n"
                );
            }


    }
}