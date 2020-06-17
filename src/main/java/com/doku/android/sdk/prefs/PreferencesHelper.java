package com.doku.android.sdk.prefs;

import javax.inject.Singleton;

/**
 * Created by dedyeirawan on 21,May,2020
 */
@Singleton
public interface PreferencesHelper {

    String getClientId();

    void setClientId(String clientId);

    String getSharedKey();

    void setSharedKey(String sharedkey);
}
