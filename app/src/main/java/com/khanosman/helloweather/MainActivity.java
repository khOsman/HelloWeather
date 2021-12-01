package com.khanosman.helloweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static RecyclerView mRecyclerView;
    public static  ModelAdapter modelAdapter;

    public EditText city;
    public  Button searchBtn;
    public static final DecimalFormat df = new DecimalFormat("0.00");

    String cityName="Dhaka";
    String api = "c31dec4ca393e45a1c76b5547d798b18";
    String URL ="http://api.openweathermap.org/data/2.5/forecast?q="+cityName+"&appid="+api;

    public static ArrayList<Model> models = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        city = findViewById(R.id.input);
        searchBtn = findViewById(R.id.searchBtn);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cName = city.getText().toString();
                URL ="http://api.openweathermap.org/data/2.5/forecast?q="+cName+"&appid="+api;
                Toast.makeText(MainActivity.this,URL,Toast.LENGTH_LONG).show();
                Weather w = new Weather();
                w.execute(URL);
                city.setText("");
            }
        });


        Weather weather = new Weather();
        weather.execute(URL);






    }

}