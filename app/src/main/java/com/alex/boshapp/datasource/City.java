package com.alex.boshapp.datasource;

import android.support.annotation.NonNull;

import org.parceler.Parcel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asvetlichniy on 11/24/17.
 */

@Parcel(Parcel.Serialization.BEAN)
public class City implements Comparable<City> {

    private String city;
    private String cityASCII;
    private String latitude;
    private String longitude;
    private String population;
    private String country;
    private String iso2;
    private String iso3;
    private String province;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityASCII() {
        return cityASCII;
    }

    public void setCityASCII(String cityASCII) {
        this.cityASCII = cityASCII;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public int compareTo(@NonNull City o) {
        return city.compareTo(o.getCity());
    }
}
