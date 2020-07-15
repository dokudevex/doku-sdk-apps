package com.doku.android.sdk.ui.resultpage;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.doku.android.sdk.BuildConfig;
import com.doku.android.sdk.R;
import com.doku.android.sdk.adapter.ExpandableRecyclerAdapter;
import com.doku.android.sdk.adapter.HowToInstructionAdapter;
import com.doku.android.sdk.main.DetailPayment;
import com.doku.android.sdk.model.MandiriHowToPayResponse;
import com.doku.android.sdk.ui.base.BaseActivity;
import com.doku.android.sdk.utils.PaymentChannel;
import com.doku.android.sdk.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

public class ResultPageActivity extends BaseActivity implements ResultPageMvpView {
    private TextView toolbarPaymentresultTitle;
    @Inject
    ResultPageMvpPresenter<ResultPageMvpView, ResultPageMvpInteractor> resultPagePresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ResultPageActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultpage_activity);

        getActivityComponent().inject(this);

        resultPagePresenter.onAttach(ResultPageActivity.this);
        setUp();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void setUp() {
        toolbarPaymentresultTitle = findViewById(R.id.toolbar_paymentresult_title);
        TextView textviewPaymentresultOrderid = findViewById(R.id.textview_paymentresult_orderid);
        TextView textviewPaymentresultTotalrp = findViewById(R.id.textview_paymentresult_totalrp);
        TextView textviewPaymentresultChannel = findViewById(R.id.textview_paymentresult_channel);
        TextView textviewPaymentresultVirtualnumber = findViewById(R.id.textview_paymentresult_virtualnumber);
        ImageView imageviewPaymentresultChannelnumber = findViewById(R.id.imageview_paymentresult_channelnumber);
        TextView textViewPaymentresultExpiredTime = findViewById(R.id.textview_paymentresult_completepaymentbeforetime);


        ConstraintLayout buttonPaymentpageChange = findViewById(R.id.button_paymentpage_change_box);
        buttonPaymentpageChange.setOnClickListener(view -> finish());

        TextView textviewPaymentresultCopy = findViewById(R.id.textview_paymentresult_copy);
        textviewPaymentresultCopy.setOnClickListener(view -> {
            ClipboardManager cManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData cData = ClipData.newPlainText("text", textviewPaymentresultVirtualnumber.getText().toString());
            cManager.setPrimaryClip(cData);
            Toast.makeText(getApplicationContext(), "Copy data berhasil",Toast.LENGTH_SHORT).show();
        });

        Button buttonResultPageDetails =  findViewById(R.id.button_paymentresult_details);
        buttonResultPageDetails.setOnClickListener(view -> {
            DetailPayment addPhotoBottomDialogFragment = DetailPayment.newInstance();
            addPhotoBottomDialogFragment.show(getSupportFragmentManager(), DetailPayment.TAG);
        });

        Bundle dataParams = getIntent().getExtras();
        if (dataParams != null) {
            String merchantName = dataParams.getString("merchantName");
            toolbarPaymentresultTitle.setText(merchantName);
            String noVa = dataParams.getString("noVa");
            textviewPaymentresultVirtualnumber.setText(noVa);
            String orderId = dataParams.getString("invoiceNumber");
            textviewPaymentresultOrderid.setText("Order ID "+ orderId);
            String amount = dataParams.getString("amount");
            String expiredTime = stringDateTimeProcessor(dataParams.getString("expiredTime")) + " WIB";
            textViewPaymentresultExpiredTime.setText(expiredTime);

            textviewPaymentresultTotalrp.setText(Utils.formatCurrencyRupiah(Double.parseDouble(amount)));
            int channelId = dataParams.getInt("channelId");
            if (channelId == PaymentChannel.VA_MANDIRI.getValue()) {
                imageviewPaymentresultChannelnumber.setBackgroundResource(R.drawable.logo_mandiri);
                textviewPaymentresultChannel.setText(getResources().getString(R.string.bank_mandiri));
                boolean isProduction = dataParams.getBoolean("isProduction");
                if (isProduction) {
                    RetrofitUrlManager.getInstance().putDomain("isProduction", BuildConfig.BASE_URL_PRODUCTION);
                }
                resultPagePresenter.getHowTopPaymandiri(noVa);
            } else if (channelId == PaymentChannel.VA_SYARIAH_MANDIRI.getValue()) {
                imageviewPaymentresultChannelnumber.setBackgroundResource(R.drawable.logo_mandiri_syariah);
                textviewPaymentresultChannel.setText(getResources().getString(R.string.bank_mandiri_syariah));

                boolean isProduction = dataParams.getBoolean("isProduction");
                if (isProduction) {
                    RetrofitUrlManager.getInstance().putDomain("isProduction", BuildConfig.BASE_URL_PRODUCTION);
                }
                resultPagePresenter.getHowTopPaymandiriSyariah(noVa);
            }
        }
    }


    private String stringDateTimeProcessor(String expiredTimeRaw){
        Date expiredTime = new Date();

        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMddhhmmss");
        try {
            expiredTime = timeFormatter.parse(expiredTimeRaw);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat timeFormatterToOutput = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        String expiredTimeOuput = timeFormatterToOutput.format(expiredTime);
        return expiredTimeOuput;

    }

    @Override
    public void showHowInstruction(MandiriHowToPayResponse data) {
        RecyclerView recyclerviewPaymentresultMain = findViewById(R.id.recyclerview_paymentresult_main);
        HowToInstructionAdapter howToInstructionAdapter = new HowToInstructionAdapter(ResultPageActivity.this, data);
        howToInstructionAdapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
        recyclerviewPaymentresultMain.setLayoutManager(new LinearLayoutManager(ResultPageActivity.this));
        recyclerviewPaymentresultMain.setAdapter(howToInstructionAdapter);
    }

    @Override
    public void errorMessage(String titileError, String errorMessage) {
        Toast.makeText(getApplicationContext(), titileError + " = " +errorMessage,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        resultPagePresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onFragmentAttached() {}

    @Override
    public void onFragmentDetached(String tag) {}

    @Override
    public void onBackPressed() {
        finish();
    }
}
