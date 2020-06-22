package com.doku.android.sdk.ui.va.mandirisyariah;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.doku.android.sdk.BuildConfig;
import com.doku.android.sdk.R;
import com.doku.android.sdk.model.MandiriVaAccountParams;
import com.doku.android.sdk.model.MandiriVaClientParams;
import com.doku.android.sdk.model.MandiriVaCustomerParams;
import com.doku.android.sdk.model.MandiriVaOrdersParams;
import com.doku.android.sdk.model.MandiriVaParams;
import com.doku.android.sdk.model.MandiriVaResponse;
import com.doku.android.sdk.model.MandiriVaSecurityParams;
import com.doku.android.sdk.ui.base.BaseActivity;
import com.doku.android.sdk.ui.resultpage.ResultPageActivity;
import com.doku.android.sdk.utils.PaymentChannel;
import com.google.gson.Gson;
import javax.inject.Inject;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

public class MandiriSyariahVaActivity extends BaseActivity implements MandiriSyariahVaMvpView {

    @Inject
    MandiriSyariahVaMvpPresenter<MandiriSyariahVaMvpView, MandiriSyariahVaMvpInteractor> mandiriSyariahPresenter;

    private ProgressDialog progressDialog;
    private String amount = "0";
    private String merchantName = "";
    private Boolean isProduction = false;
    private Boolean usePageResult = false;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MandiriSyariahVaActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mandirisyariahva_activity);

        getActivityComponent().inject(this);

        mandiriSyariahPresenter.onAttach(MandiriSyariahVaActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("VA Syariah Mandiri");
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.show();

        Bundle dataParams = getIntent().getExtras();
        if (dataParams != null) {
            merchantName = dataParams.getString("merchantName");
            String clientId = dataParams.getString("clientId");
            String invoiceNumber = dataParams.getString("invoiceNumber");
            amount = dataParams.getString("amount");
            String expiredTime = dataParams.getString("expiredTime");
            String reusableStatus = dataParams.getString("reusableStatus");
            String info1 = dataParams.getString("info1");
            String info2 = dataParams.getString("info2");
            String info3 = dataParams.getString("info3");
            String customerName = dataParams.getString("customerName");
            String customerEmail = dataParams.getString("customerEmail");
            String words = dataParams.getString("words");
            usePageResult = dataParams.getBoolean("usePageResult");

            MandiriVaClientParams mandiriVaClientParams = new MandiriVaClientParams();
            mandiriVaClientParams.id = clientId;

            MandiriVaOrdersParams mandiriVaOrdersParams = new MandiriVaOrdersParams();
            mandiriVaOrdersParams.invoiceNumber = invoiceNumber;
            mandiriVaOrdersParams.amount = Integer.valueOf(amount);

            MandiriVaAccountParams mandiriVaAccountParams = new MandiriVaAccountParams();
            mandiriVaAccountParams.expiredTime = Integer.valueOf(expiredTime);
            mandiriVaAccountParams.reusableStatus = reusableStatus;
            mandiriVaAccountParams.info1 = info1;
            mandiriVaAccountParams.info2 = info2;
            mandiriVaAccountParams.info3 = info3;

            MandiriVaCustomerParams mandiriVaCustomerParams = new MandiriVaCustomerParams();
            mandiriVaCustomerParams.email = customerEmail;
            mandiriVaCustomerParams.name = customerName;

            MandiriVaSecurityParams mandiriVaSecurityParams = new MandiriVaSecurityParams();
            mandiriVaSecurityParams.checkSum = words;

            MandiriVaParams mandiriVaParams = new MandiriVaParams();
            mandiriVaParams.client = mandiriVaClientParams;
            mandiriVaParams.order = mandiriVaOrdersParams;
            mandiriVaParams.virtualAccountInfo = mandiriVaAccountParams;
            mandiriVaParams.customer = mandiriVaCustomerParams;
            mandiriVaParams.security = mandiriVaSecurityParams;

            isProduction = dataParams.getBoolean("isProduction");
            if (isProduction) {
                RetrofitUrlManager.getInstance().putDomain("isProduction", BuildConfig.BASE_URL_PRODUCTION);
            }
            mandiriSyariahPresenter.getPaymentCode(mandiriVaParams);
        } else {
            finish();
        }
    }

    @Override
    public void showResult(MandiriVaResponse data) {
        progressDialog.dismiss();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("dataResponse", new Gson().toJson(data));
        setResult(RESULT_OK, returnIntent);

        if (usePageResult) {
            Intent intent = ResultPageActivity.getStartIntent(MandiriSyariahVaActivity.this);
            intent.putExtra("invoiceNumber", data.getOrder().getInvoiceNumber());
            intent.putExtra("noVa", data.getVirtualAccountInfo().getVirtualAccountNumber());
            intent.putExtra("amount", amount);
            intent.putExtra("channelId", PaymentChannel.VA_SYARIAH_MANDIRI.getValue());
            intent.putExtra("isProduction", isProduction);
            intent.putExtra("merchantName", merchantName);
            intent.putExtra("expiredTime", data.getVirtualAccountInfo().getExpiredDate());
            startActivity(intent);
        }
        finish();
    }

    @Override
    public void errorMessage(String titileError, String errorMessage) {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), titileError + " = " +errorMessage,Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        mandiriSyariahPresenter.onDetach();
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
