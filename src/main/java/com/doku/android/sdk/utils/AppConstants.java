package com.doku.android.sdk.utils;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public final class AppConstants {

    public static final long NULL_INDEX = -1L;
    public static final String PREF_NAME = "dokupay_sdk_pref";

    private AppConstants() {}

    public enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
