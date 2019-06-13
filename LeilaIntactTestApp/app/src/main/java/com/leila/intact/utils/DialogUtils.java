package com.leila.intact.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.leila.intact.view.ProductListFragment;
import com.leila.intact.view.R;

public class DialogUtils {

    public static AlertDialog showConfirmationDialog(Activity activity, ProductListFragment.DialogPositiveClickListener positiveButton) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialoug_confirmation, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();


        Button positiveTextView = dialogView.findViewById(R.id.dialog_confirm_positive);
        positiveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positiveButton.didOkButtonClicked();
                alertDialog.dismiss();
            }
        });

        Button negativeTextView = dialogView.findViewById(R.id.dialog_confirm_negative);
        negativeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

        return alertDialog;
    }
}
