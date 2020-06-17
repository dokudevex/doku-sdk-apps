package com.doku.android.sdk.api;

import com.doku.android.sdk.config.UrlConfig;
import com.doku.android.sdk.model.MandiriHowToPayResponse;
import com.doku.android.sdk.model.MandiriVaParams;
import com.doku.android.sdk.model.MandiriVaResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public interface ApiService {

    @Headers({"Domain-Name: isProduction"})
    @POST(UrlConfig.URL_VA_SYARIAH_MANDIRI)
    Observable<MandiriVaResponse> mandiriSyariahVa(@Body MandiriVaParams mandiriSyariahVaParams);

    @Headers({"Domain-Name: isProduction"})
    @POST(UrlConfig.URL_VA_MANDIRI)
    Observable<MandiriVaResponse> mandiriVa(@Body MandiriVaParams mandiriVaParams);

    @Headers({"Domain-Name: isProduction"})
    @GET(UrlConfig.URL_HOW_TO_VA_MANDIRI)
    Observable<MandiriHowToPayResponse> howToPayVaMandiri(@Path(value = "noVa", encoded = true) String noVa);

    @Headers({"Domain-Name: isProduction"})
    @GET(UrlConfig.URL_HOW_TO_VA_MANDIRI_SYARIAH)
    Observable<MandiriHowToPayResponse> howToPayVaMandiriSyariah(@Path(value = "noVa", encoded = true) String noVa);
}
