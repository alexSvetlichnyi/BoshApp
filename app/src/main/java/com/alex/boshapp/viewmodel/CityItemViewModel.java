package com.alex.boshapp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.alex.boshapp.R;
import com.alex.boshapp.datasource.City;
import com.alex.boshapp.ui.MainActivityCallback;

import java.util.Random;

public class CityItemViewModel {
    private City city;
    public ObservableField<String> cityName = new ObservableField<>();
    public ObservableField<String> population = new ObservableField<>();
    public ObservableField<String> coordinates = new ObservableField<>();
    private int background;
    @NonNull
    private MainActivityCallback callback;

    public CityItemViewModel (@NonNull Context context, @NonNull City city, @NonNull MainActivityCallback callback) {
        this.city = city;
        this.cityName.set(String.format("%s, %s, %s", city.getCity(), city.getProvince(), city.getCountry()));
        this.population.set(context.getString(R.string.city_population, city.getPopulation()));
        this.coordinates.set(context.getString(R.string.city_coordinates, city.getLatitude(), city.getLongitude()));
        background = generateBackground(context);
        this.callback = callback;
    }

    private int generateBackground(Context context) {
        Random rnd = new Random();
        switch (rnd.nextInt(3)) {
            case 0:
                return ContextCompat.getColor(context, R.color.color_blue);
            case 1:
                return ContextCompat.getColor(context, R.color.color_red);
            case 2:
            default:
                return ContextCompat.getColor(context, R.color.color_green);
        }
    }

    public int getBackground() {
        return background;
    }

    public void onItemClick(View view) {
        callback.openMap(city);
    }
}
