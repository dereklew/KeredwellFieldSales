package com.keredwell.fieldsales.ui.order;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.keredwell.fieldsales.R;

import static com.keredwell.fieldsales.util.LogUtil.makeLogTag;

public class CashOrCreditDialogFragment extends DialogFragment {
    private static final String TAG = makeLogTag(CashOrCreditDialogFragment.class);

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.payment_term);
        builder.setMessage(R.string.cash_or_credit_message)
            .setPositiveButton(R.string.cash_or_credit_credit, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    OrderListActivity callingActivity = (OrderListActivity) getActivity();
                    callingActivity.onCashOrCreditSelection(false);
                    dialog.dismiss();
                }
            })
            .setNegativeButton(R.string.cash_or_credit_cash, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    OrderListActivity callingActivity = (OrderListActivity) getActivity();
                    callingActivity.onCashOrCreditSelection(true);
                    dialog.dismiss();
                }
            });
        return builder.create();
    }
}
