package com.joragupra.modernart.modernart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MoreInformationDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_explanation).setPositiveButton(R.string.dialog_option_visit,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent visit = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://www.moma.org"));
                        Intent chooser = Intent.createChooser(visit,
                                getResources().getString(R.string.dialog_option_open_with));
                        startActivity(chooser);
                    }
                }).setNegativeButton(R.string.dialog_option_not_now, null);

        return builder.create();
    }

}
