package com.khanosman.helloweather;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ModelHolder  extends RecyclerView.ViewHolder {

    TextView date,city,temp,weather;

    public ModelHolder(@NonNull View itemView) {
        super(itemView);

        this.date = itemView.findViewById(R.id.date);
        this.city = itemView.findViewById(R.id.city);
        this.temp = itemView.findViewById(R.id.temp);
        this.weather = itemView.findViewById(R.id.weather);

    }
}
