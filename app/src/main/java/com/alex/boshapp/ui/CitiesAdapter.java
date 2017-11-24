package com.alex.boshapp.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alex.boshapp.BR;
import com.alex.boshapp.R;
import com.alex.boshapp.datasource.City;
import com.alex.boshapp.viewmodel.CityItemViewModel;

import java.util.ArrayList;
import java.util.List;


public class CitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    private MainActivityCallback callback;

    CitiesAdapter(@NonNull MainActivityCallback callback) {
        this.callback = callback;
    }

    @NonNull
    private List<City> citiesList = new ArrayList<>();

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewDataBinding dataBinding = ((CityItemViewHolder) holder).dataBinding;
        CityItemViewModel cityItemViewModel = new CityItemViewModel(dataBinding.getRoot().getContext(),
                citiesList.get(position), callback);
        dataBinding.setVariable(BR.viewModel, cityItemViewModel);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewDataBinding binder = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.city_item, parent, false);
            return new CityItemViewHolder(binder);
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public static class CityItemViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding dataBinding;

        CityItemViewHolder(ViewDataBinding dataBinding) {
            super(dataBinding.getRoot());
            this.dataBinding = dataBinding;
        }
    }

    void setCitiesList(@NonNull List<City> citiesList) {
        this.citiesList = citiesList;
        notifyDataSetChanged();
    }
}
