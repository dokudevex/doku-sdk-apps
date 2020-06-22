package com.doku.android.sdk.ui.va.mandiri;

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

public class MandiriVaActivity extends BaseActivity implements MandiriVaMvpView {

    @Inject
    MandiriVaMvpPresenter<MandiriVaMvpView, MandiriVaMvpInteractor> mandiriVaPresenter;

    private ProgressDialog progressDialog;
    private String amount = "0";
    private String merchantName = "";
    private Boolean isProduction = false;
    private Boolean usePageResult = false;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MandiriVaActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mandiriva_activity);

        getActivityComponent().inject(this);

        mandiriVaPresenter.onAttach(MandiriVaActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("VA Mandiri");
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
            String customerName = dataParams.getString("customerName");
            String customerEmail = dataParams.getString("customerEmail");
            String words = dataParams.getString("words");
            usePageResult = dataParams.getBoolean("usePageResult");

            MandiriVaClientParams mandiriVaClientParams = new MandiriVaClientParams();
            mandiriVaClientParams.id = clientId;

            MandiriVaOrdersParams mandiriVaOrdersParams = new MandiriVaOrdersParams();
            mandiriVaOrdersParams.amount = Integer.valueOf(amount);
            mandiriVaOrdersParams.invoiceNumber = invoiceNumber;

            MandiriVaAccountParams mandiriVaAccountParams = new MandiriVaAccountParams();
            mandiriVaAccountParams.reusableStatus = reusableStatus;
            mandiriVaAccountParams.expiredTime = Integer.valueOf(expiredTime);
            mandiriVaAccountParams.info1 = "";
            mandiriVaAccountParams.info2 = "";
            mandiriVaAccountParams.info3 = "";

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
            mandiriVaPresenter.getPaymentCode(mandiriVaParams);
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
            Intent intent = ResultPageActivity.getStartIntent(MandiriVaActivity.this);
            intent.putExtra("invoiceNumber", data.getOrder().getInvoiceNumber());
            intent.putExtra("noVa", data.getVirtualAccountInfo().getVirtualAccountNumber());
            intent.putExtra("amount", amount);
            intent.putExtra("channelId", PaymentChannel.VA_MANDIRI.getValue());
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
        mandiriVaPresenter.onDetach();
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
