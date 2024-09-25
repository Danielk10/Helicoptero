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

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {
  
    private WakeLock wakeLock;
  
  	private PantallaCompleta pantallaCompleta;
  
  	private MostrarPublicidad publicidad;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AppCenter.start(getApplication(), "2dea6be0-8f41-49ac-b4ce-468cced27237",
                  Analytics.class, Crashes.class);
        new Thread(
                    () -> {
                      // Initialize the Google Mobile Ads SDK on a background thread.
                      MobileAds.initialize(this, initializationStatus -> {});
                    })
                .start();
                  
        pantallaCompleta = new PantallaCompleta(this);
        
        pantallaCompleta.pantallaCompleta();
        
        pantallaCompleta.ocultarBotonesVirtuales();
        
        publicidad = new MostrarPublicidad(this);
        
        publicidad.IniciarPublicidad(this);
        
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        
        RelativeLayout mainLayout = new RelativeLayout(this);
        
        FrameLayout frame = new FrameLayout(this);
        
        configuration.useImmersiveMode = true; // Recommended, but not required.
        
        RelativeLayout.LayoutParams mrecParameters = new RelativeLayout.LayoutParams(
        				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        		mrecParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        		mrecParameters.addRule(RelativeLayout.ALIGN_PARENT_TOP);
      
        
        		frame.addView(initializeForView(new Helicoptero(publicidad), configuration), new FrameLayout.LayoutParams(
        				FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        
        		frame.addView(mainLayout);
        
        		setContentView(frame);
        
        		PowerManager powerManejador = (PowerManager) getSystemService(Context.POWER_SERVICE);
        
        		wakeLock = powerManejador.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
    }
    
    @Override
    	protected void onPause() {
    
    		super.onPause();
    
    		wakeLock.release();
    
    	}
    
    	@Override
    	protected void onResume() {
    
    		super.onResume();
    
    		wakeLock.acquire();
    
    	}
    
    	@Override
    	public void onWindowFocusChanged(boolean hasFocus) {
    		super.onWindowFocusChanged(hasFocus);
    
    		if (hasFocus) {
    
    			pantallaCompleta.ocultarBotonesVirtuales();
    		}
    
    	}
    
    	@Override
    	public boolean onKeyUp(int keyCode, KeyEvent event) {
    
    		pantallaCompleta.ocultarBotonesVirtuales();
    
    		return super.onKeyUp(keyCode, event);
    	}
}