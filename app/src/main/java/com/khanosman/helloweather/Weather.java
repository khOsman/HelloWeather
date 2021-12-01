package com.khanosman.helloweather;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Weather extends AsyncTask<String,Void,String>{


    String result;
    @Override
    protected String doInBackground(String... urls) {

        result="";

        URL link;
        HttpURLConnection httpURLConnection = null;

        try {
            link = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection)link.openConnection();
            InputStream ins = httpURLConnection.getInputStream();
            InputStreamReader insr = new InputStreamReader(ins);
            int data = insr.read();
            while (data!=-1){
                char current = (char)data;
                result+=current;
                data = insr.read();
            }

            return result;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {

            JSONObject weatherObject = new JSONObject(result);
            JSONObject cityObj = new JSONObject(weatherObject.getString("city"));
            JSONArray list = weatherObject.getJSONArray("list");
            String cityName = cityObj.getString("name");
            String countryName = cityObj.getString("country");
            String city_Country = cityName+", "+countryName;
//            Log.d("_weather : city : ",cityName);
//            Log.d("_weather : country : ",countryName);

            ArrayList<Model> modelList = new ArrayList<>();
            for (int i=0;i<list.length();i++){
                String date = list.getJSONObject(i).getString("dt_txt");
                JSONObject main = new JSONObject(list.getJSONObject(i).getString("main"));
                double tempD = main.getDouble("temp");
                tempD= tempD-273.15;
                String temp = ""+ MainActivity.df.format(tempD)+" °C";

                JSONArray weatherList = list.getJSONObject(i).getJSONArray("weather");
                String weather = weatherList.getJSONObject(0).getString("main");

                String test = date+" -> "+city_Country+" "+temp+" °C "+weather;
                Log.d("_weather : ",test);

                Model m = new Model();
                m.setDate("Date & Time : "+date);
                m.setCity("Place : "+city_Country);
                m.setTemp("Temperature : "+temp);
                m.setWeather("Weather : "+weather);
                modelList.add(m);
            }

            MainActivity.modelAdapter = new ModelAdapter(MainActivity.class,modelList);
            MainActivity.mRecyclerView.setAdapter( MainActivity.modelAdapter);
//           MainActivity.city.setText(city);
//           MainActivity.temperature.setText(tem);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
