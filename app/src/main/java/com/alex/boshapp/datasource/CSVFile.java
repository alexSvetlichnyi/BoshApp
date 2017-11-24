package com.alex.boshapp.datasource;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asvetlichniy on 11/24/17.
 */

public class CSVFile {
    public List<City> read(InputStream inputStream) {
        List<City> resultList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(";");

                if (row.length == 9) {
                    City city = new City();
                    city.setCity(row[0]);
                    city.setCityASCII(row[1]);
                    city.setLatitude(row[2]);
                    city.setLongitude(row[3]);
                    city.setPopulation(row[4]);
                    city.setCountry(row[5]);
                    city.setIso2(row[6]);
                    city.setIso3(row[7]);
                    city.setProvince(row[8]);
                    resultList.add(city);
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    public void writeData(City city, File file) {
        PrintWriter csvWriter;
        try {
            csvWriter = new PrintWriter(new FileWriter(file, true));
            String[] row = new String[9];

            row[0] = city.getCity();
            row[1] = city.getCityASCII();
            row[2] = city.getLatitude();
            row[3] = city.getLongitude();
            row[4] = city.getPopulation();
            row[5] = city.getCountry();
            row[6] = city.getIso2();
            row[7] = city.getIso3();
            row[8] = city.getProvince();
            String csvLine = TextUtils.join(";", row) + "\n";
            csvWriter.append(csvLine);
            csvWriter.flush();
            csvWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
