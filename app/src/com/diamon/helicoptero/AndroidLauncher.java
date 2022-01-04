package com.diamon.helicoptero;

import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

import com.startapp.sdk.ads.banner.Banner;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.diamon.publicidad.MostrarPublicidad;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.diamon.helicoptero.Helicoptero;


import com.diamon.pantalla.PantallaCompleta;
import android.content.*;
import android.view.*;

public class AndroidLauncher extends AndroidApplication {

	private WakeLock wakeLock;

	private PantallaCompleta pantallaCompleta;

	private MostrarPublicidad publicidad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		pantallaCompleta = new PantallaCompleta(this);

		pantallaCompleta.pantallaCompleta();

		pantallaCompleta.ocultarBotonesVirtuales();

		publicidad = new MostrarPublicidad(this);
		
		publicidad.IniciarPublicidad(this);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		// initialize(new Helicoptero(), config);

		RelativeLayout mainLayout = new RelativeLayout(this);

		FrameLayout frame = new FrameLayout(this);

		Banner baner = new Banner(this);

		baner.setScaleX(0.5f);

		baner.setScaleY(0.5f);

		RelativeLayout.LayoutParams mrecParameters = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		mrecParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mrecParameters.addRule(RelativeLayout.ALIGN_PARENT_TOP);

		mainLayout.addView(baner, mrecParameters);

		frame.addView(initializeForView(new Helicoptero(), config), new FrameLayout.LayoutParams(
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
