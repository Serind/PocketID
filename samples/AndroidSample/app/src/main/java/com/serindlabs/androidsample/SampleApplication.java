package com.serindlabs.androidsample;

import android.app.Application;

import com.serindlabs.pocketid.sdk.PocketIDSdk;
import com.serindlabs.pocketid.sdk.constants.PocketIDTheme;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PocketIDSdk.getInstance()
                .initialize(this, "nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)");
        PocketIDSdk.getInstance()
                .customize()
                .setLogo(R.mipmap.ic_launcher_round)
                .setAppName(getString(R.string.app_name))
                .setTheme(PocketIDTheme.LIGHT);
    }
}
