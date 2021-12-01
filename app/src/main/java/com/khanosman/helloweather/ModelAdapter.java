package com.khanosman.helloweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModelAdapter  extends RecyclerView.Adapter<ModelHolder> {

    Class<MainActivity> context;
    ArrayList<Model> models;

    public ModelAdapter(Class<MainActivity> context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }


    @NonNull
    @Override
    public ModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);

        return new ModelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelHolder holder, int position) {
        //date,city,temp,weather;
        holder.date.setText(models.get(position).getDate());
        holder.city.setText(models.get(position).getCity());
        holder.temp.setText(models.get(position).getTemp());
        holder.weather.setText(models.get(position).getWeather());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
