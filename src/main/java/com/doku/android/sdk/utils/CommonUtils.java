package com.doku.android.sdk.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.doku.android.sdk.R;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public final class CommonUtils {
    private CommonUtils() {}

    public static ProgressDialog showLoadingDialog(Context context) {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();

        if(progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static void showDWAlertDialog(final Context context, String alert, String title, String btnOkText, final DialogInterface.OnClickListener okListener) {

        alert = alert + "<br> &nbsp";
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);

        View content = ((Activity) context).getLayoutInflater().inflate(R.layout.alertdialog_all, null);
        TextView txt_title = content.findViewById(R.id.txt_title);
        txt_title.setText(title);

        ((TextView) content.findViewById(R.id.tvAlertBody)).setText(Html.fromHtml(alert != null && !alert.equals("") ? alert : "Unexpected Error, please contact our customer service.!"));
        builder.setView(content);

        if (okListener != null) {
            builder.setPositiveButton((btnOkText != null ? btnOkText : "OK"), okListener);
        } else {
            builder.setPositiveButton((btnOkText != null ? btnOkText : "OK"),
                    (arg0, arg1) -> {

                    });
        }

        final AlertDialog dialog = builder.create();
        dialog.setView(content);
        dialog.show();
    }
}
