package com.example.weatherapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.model.Weather;

import java.util.List;

public class CityReyclerViewAdapter extends RecyclerView.Adapter<CityReyclerViewAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Weather> list;
    private Context context;
    private OnCityClickedListener listener;

    CityReyclerViewAdapter(Context context, OnCityClickedListener listener) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.city_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Weather current = list.get(position);
        holder.tvCityName.setText(current.getCountry_name());
        holder.tvCityTemp.setText(String.format(context.getString(R.string.temp_sign), current.getTempCelsius()));
        holder.bind(current);
    }

    public void setWeatherList(List<Weather> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list != null? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        TextView tvCityName;
        TextView tvCityTemp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvCityName = itemView.findViewById(R.id.tv_city_name);
            tvCityTemp = itemView.findViewById(R.id.tv_city_temp);
        }

        void bind(final Weather weather) {
            if (listener != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onCityClicked(weather);
                    }
                });
            }
        }
    }

    interface OnCityClickedListener {
        void onCityClicked(Weather weather);
    }
}
