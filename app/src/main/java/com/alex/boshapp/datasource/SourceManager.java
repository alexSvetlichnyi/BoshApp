package com.alex.boshapp.datasource;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asvetlichniy on 11/24/17.
 */

public interface SourceManager {

   List<City> getWorldCities(Context context);

   void addCityToList(City city, Context context);
}
