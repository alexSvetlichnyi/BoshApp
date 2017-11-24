package com.alex.boshapp.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.alex.boshapp.R;
import com.alex.boshapp.databinding.AddCityDialogBinding;
import com.alex.boshapp.viewmodel.AddCityViewModel;

public class AddCityDialog {
    private Context context;
    private MainActivityCallback callback;

    public AddCityDialog(final @NonNull Context context, final @NonNull MainActivityCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void show() {
        AddCityDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.add_city_dialog, null, false);
        final AddCityViewModel viewModel = new AddCityViewModel();
        binding.setViewModel(viewModel);
        View mView = binding.getRoot();
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        alertDialogBuilderUserInput.setView(mView);

        alertDialogBuilderUserInput
                .setTitle(context.getString(R.string.add_city_dialog_title))
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.add_dialog), null)
                .setNegativeButton(context.getString(R.string.cancel_dialog), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });
        final AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
        alertDialogAndroid.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.isInputValid()) {
                    callback.addCityToTheList(viewModel.city);
                    alertDialogAndroid.dismiss();
                } else {
                    Toast.makeText(context, R.string.fill_all_fields_message, Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialogAndroid.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
}
