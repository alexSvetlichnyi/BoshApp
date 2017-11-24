package com.alex.boshapp.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.view.View;

import com.alex.boshapp.BR;
import com.alex.boshapp.datasource.City;
import com.alex.boshapp.ui.MainActivityCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends BaseObservable {

    private static final int DEFAULT_SEARCH_LENGTH = 0;

    @NonNull
    private List<City> cities = new ArrayList<>();
    @NonNull
    private List<City> previousSearch = new ArrayList<>();
    @Bindable
    private String searchCity = "";
    @NonNull
    private MainActivityCallback callback;

    public MainActivityViewModel(@NonNull List<City> cities, @NonNull MainActivityCallback callback) {
        this.cities = cities;
        this.previousSearch = cities;
        this.callback = callback;
    }

    public String getSearchCity() {
        return searchCity;
    }

    public void setSearchCity(String searchCity) {
        if (searchCity != null && searchCity.length() >= DEFAULT_SEARCH_LENGTH) {
            findCities(searchCity);
        }
        this.searchCity = searchCity;
        notifyPropertyChanged(BR.citiesVisibility);
        notifyPropertyChanged(BR.emptyMessageVisibility);
    }

    @NonNull
    @VisibleForTesting(otherwise = 5)
    public List<City> getPreviousSearch() {
        return previousSearch;
    }

    @Bindable
    public int getCitiesVisibility() {
        return previousSearch.size() != 0 ? View.VISIBLE : View.GONE;
    }

    @Bindable
    public int getEmptyMessageVisibility() {
        return previousSearch.size() != 0 ? View.GONE : View.VISIBLE;
    }

    private void findCities(@NonNull String searchCity) {
        List<City> results = new ArrayList<>();
        List<City> forSearch = this.searchCity.length() < searchCity.length() && searchCity.startsWith(this.searchCity) ? previousSearch : cities;
        for (City city : forSearch) {
            if (city.getCity().toLowerCase().startsWith(searchCity.toLowerCase())) {
                results.add(city);
            }
        }
        previousSearch = results;
        callback.updateList(results);
    }

    public void onItemClick(View view) {
        callback.addCityClick();
    }

    public void addCityToList(City city) {
        cities.add(city);
        previousSearch.add(city);
    }
}
