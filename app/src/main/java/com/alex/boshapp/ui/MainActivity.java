package com.alex.boshapp.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alex.boshapp.R;
import com.alex.boshapp.databinding.ActivityMainBinding;
import com.alex.boshapp.datasource.City;
import com.alex.boshapp.datasource.SourceManager;
import com.alex.boshapp.datasource.SourceManagerImpl;
import com.alex.boshapp.viewmodel.MainActivityViewModel;

import org.parceler.Parcels;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityCallback {
    SourceManager sourceManager = new SourceManagerImpl();
    CitiesAdapter adapter;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        List<City> worldCities = sourceManager.getWorldCities(this);
        viewModel = new MainActivityViewModel(worldCities, this);
        adapter = new CitiesAdapter(this);
        setupRecycleView(binding, worldCities);
        binding.setViewModel(viewModel);
        initToolbar();
    }

    private void setupRecycleView(ActivityMainBinding binding, List<City> worldCities) {
        final RecyclerView recyclerView = binding.rvCities;
        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setCitiesList(worldCities);
    }

    private void initToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.app_bar));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void updateList(List<City> cities) {
        adapter.setCitiesList(cities);
    }

    @Override
    public void openMap(City city) {
        Intent intent = new Intent(MainActivity.this, MapViewActivity.class);
        intent.putExtra(MapViewActivity.CITY, Parcels.wrap(city));
        startActivity(intent);
    }

    @Override
    public void addCityClick() {
        AddCityDialog dialog = new AddCityDialog(this, this);
        dialog.show();
    }

    @Override
    public void addCityToTheList(City city) {
        viewModel.addCityToList(city);
        sourceManager.addCityToList(city, this);
    }
}
