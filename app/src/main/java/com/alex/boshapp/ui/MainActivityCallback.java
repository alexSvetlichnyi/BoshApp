package com.alex.boshapp.ui;

import com.alex.boshapp.datasource.City;

import java.util.List;

public interface MainActivityCallback {
   void updateList(List<City> cities);
   void openMap(City city);
   void addCityClick();
   void addCityToTheList(City city);
}
