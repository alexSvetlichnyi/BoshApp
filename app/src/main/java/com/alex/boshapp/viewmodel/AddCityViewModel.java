package com.alex.boshapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.alex.boshapp.R;
import com.alex.boshapp.datasource.City;
import com.alex.boshapp.ui.MainActivityCallback;

import java.util.Random;

public class AddCityViewModel {
    public City city;

    public AddCityViewModel() {
        this.city = new City();
        city.setIso2("df");
        city.setIso3("dfr");
        city.setCityASCII("dfr");
    }

    public boolean isInputValid() {
        return !TextUtils.isEmpty(city.getCity()) &&
                !TextUtils.isEmpty(city.getCountry()) && !TextUtils.isEmpty(city.getLatitude()) &&
                !TextUtils.isEmpty(city.getLongitude()) && !TextUtils.isEmpty(city.getPopulation()) &&
                !TextUtils.isEmpty(city.getProvince());
    }
}
