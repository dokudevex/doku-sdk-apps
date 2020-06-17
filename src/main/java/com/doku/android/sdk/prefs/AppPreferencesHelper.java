package com.doku.android.sdk.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import com.doku.android.sdk.di.ApplicationContext;
import com.doku.android.sdk.di.PreferenceInfo;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by dedyeirawan on 21,May,2020
 */
@Singleton
public class AppPreferencesHelper implements PreferencesHelper {
    private static final String PREF_KEY_CURRENT_CLIENT_ID = "PREF_KEY_CURRENT_CLIENT_ID";
    private static final String PREF_KEY_CURRENT_SHARED_KEY = "PREF_KEY_CURRENT_SHARED_KEY";
    private final SharedPreferences mPrefs;

    @Inject
    AppPreferencesHelper(@ApplicationContext Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getClientId() {
        return mPrefs.getString(PREF_KEY_CURRENT_CLIENT_ID, null);
    }

    @Override
    public void setClientId(String loginName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_CLIENT_ID, loginName).apply();
    }

    @Override
    public String getSharedKey() {
        return mPrefs.getString(PREF_KEY_CURRENT_SHARED_KEY, null);
    }

    @Override
    public void setSharedKey(String token) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_SHARED_KEY, token).apply();
    }
}
