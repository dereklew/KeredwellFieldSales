package com.keredwell.fieldsales.ui.order;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.keredwell.fieldsales.ApplicationContext;
import com.keredwell.fieldsales.R;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

/**
 * Created by Derek on 4/9/2017.
 */

public class PrintDialogFragment extends DialogFragment {
    private static final String TAG = makeLogTag(PrintDialogFragment.class);

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(ApplicationContext.getAppContext().getString(R.string.synchronization_title));
        builder.setMessage(ApplicationContext.getAppContext().getString(R.string.succeeded) + ApplicationContext.getAppContext().getString(R.string.wanttoprint))
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    OrderCheckoutActivity callingActivity = (OrderCheckoutActivity) getActivity();
                    callingActivity.onPrint(true);
                    dialog.dismiss();
                }
            })
            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    OrderCheckoutActivity callingActivity = (OrderCheckoutActivity) getActivity();
                    callingActivity.onPrint(false);
                    dialog.dismiss();
                }
            });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
