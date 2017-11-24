package com.alex.boshapp.datasource;

import android.content.Context;

import com.alex.boshapp.R;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

/**
 * Created by asvetlichniy on 11/24/17.
 */

public class SourceManagerImpl implements SourceManager {

    @Override
    public List<City> getWorldCities(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.world_cities);
        CSVFile file = new CSVFile();
        return file.read(inputStream);
    }

    @Override
    public void addCityToList(City city, Context context) {
        File file = new File(context.getFilesDir() + File.separator + "world_cities.csv");
        CSVFile csvFile = new CSVFile();
        csvFile.writeData(city, file);
    }
}
