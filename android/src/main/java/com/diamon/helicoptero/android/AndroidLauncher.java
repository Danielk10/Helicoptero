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
import androidx.annotation.NonNull;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.RequestConfiguration;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {
  
    private static final String AD_UNIT_ID = "ca-app-pub-5141499161332805/5839846237";
  
    private WakeLock wakeLock;
  
  	private PantallaCompleta pantallaCompleta;
  
  	private MostrarPublicidad publicidad;
  	
  	private AdView adView;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AppCenter.start(getApplication(), "2dea6be0-8f41-49ac-b4ce-468cced27237",
                  Analytics.class, Crashes.class);
      
                
                
        	MobileAds.initialize(this, new OnInitializationCompleteListener() {
                		            @Override
                		            public void onInitializationComplete(InitializationStatus initializationStatus) {
                		            }
                		    });
                				
          
        pantallaCompleta = new PantallaCompleta(this);
        
        pantallaCompleta.pantallaCompleta();
        
        pantallaCompleta.ocultarBotonesVirtuales();
        
        publicidad = new MostrarPublicidad(this);
        
        publicidad.IniciarPublicidad(this);
        
              // Create a new ad view.
              adView = new AdView(this);
              adView.setAdUnitId(AD_UNIT_ID);
              
              adView.setAdSize(AdSize.BANNER);
      
              AdRequest adRequest = new AdRequest.Builder().build();

          
              adView.loadAd(adRequest);
              // [END load_ad]
        
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        
        RelativeLayout mainLayout = new RelativeLayout(this);
        
        FrameLayout frame = new FrameLayout(this);
        
        configuration.useImmersiveMode = true; // Recommended, but not required.
        
        RelativeLayout.LayoutParams mrecParameters = new RelativeLayout.LayoutParams(
        				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        		mrecParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        		mrecParameters.addRule(RelativeLayout.ALIGN_PARENT_TOP);
      
        mainLayout.addView(adView, mrecParameters);
        		frame.addView(initializeForView(new Helicoptero(publicidad), configuration), new FrameLayout.LayoutParams(
        				FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        
        		frame.addView(mainLayout);
        
        		setContentView(frame);
        
        		PowerManager powerManejador = (PowerManager) getSystemService(Context.POWER_SERVICE);
        
        		wakeLock = powerManejador.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
    }
    
    @Override
    	protected void onPause() {
    	  
    	      if (adView != null) {
    	        adView.pause();
    	      }
    
    		super.onPause();
    		
    		
    
    		wakeLock.release();
    
    	}
    
    	@Override
    	protected void onResume() {
    
    		super.onResume();
    		
    		    if (adView != null) {
    		      adView.resume();
    		    }    if (adView != null) {
    		          adView.resume();
    		        }
    		
    
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