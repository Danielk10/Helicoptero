package com.diamon.helicoptero.android;

import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.diamon.publicidad.MostrarPublicidad;
import com.diamon.pantalla.PantallaCompleta;

import android.content.*;
import android.view.*;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;



import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.diamon.helicoptero.Helicoptero;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AppCenter.start(getApplication(), "2dea6be0-8f41-49ac-b4ce-468cced27237",
                  Analytics.class, Crashes.class);
        
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useImmersiveMode = true; // Recommended, but not required.
        initialize(new Helicoptero(), configuration);
    }
}