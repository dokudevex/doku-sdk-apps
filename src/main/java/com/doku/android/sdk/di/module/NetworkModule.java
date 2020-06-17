package com.doku.android.sdk.di.module;

import android.content.Context;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.doku.android.sdk.BuildConfig;
import com.doku.android.sdk.di.ApplicationContext;
import com.doku.android.sdk.api.ApiService;
import com.doku.android.sdk.utils.CookieInterceptor;
import com.doku.android.sdk.utils.TLSSocketFactory;
import java.io.File;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import dagger.Module;
import dagger.Provides;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by dedyeirawan on 21,May,2020
 */
@Module
public class NetworkModule {
    private static final int CONNECT_TIMEOUT_IN_MS = 60000;

    @Provides
    @Singleton
    OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache, @ApplicationContext Context context) {
        try {
        CookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

        ConnectionSpec spec;
        final X509TrustManager trustAllCerts = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                java.security.cert.X509Certificate[] chain,
                String authType) {/*not implemented*/}

                @Override
                public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) {/*not implemented*/}

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
        };

        final SSLSocketFactory sslSocketFactory = new TLSSocketFactory();
        spec = new ConnectionSpec.Builder(ConnectionSpec.RESTRICTED_TLS).allEnabledCipherSuites().build();

        return RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder())
                .connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .readTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .writeTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                //.sslSocketFactory(sslSocketFactory,trustAllCerts)
                //.connectionSpecs(Collections.singletonList(spec))
                .retryOnConnectionFailure(false)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new CookieInterceptor())
                .cookieJar(cookieJar)
                .cache(cache)
                .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    @Singleton
    Retrofit retrofit(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder().setLenient().setDateFormat("MMM dd,yyyy HH:mm:ss").create();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_SANDBOX)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Timber.i(message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000); //10MB Cache
    }

    @Provides
    @Singleton
    File cacheFile(@ApplicationContext Context context) {
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @Singleton
    @Provides
    ApiService apiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}