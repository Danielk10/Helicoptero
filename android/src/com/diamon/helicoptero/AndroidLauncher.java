package com.diamon.helicoptero;

import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.diamon.helicoptero.Helicoptero;

import com.diamon.pantalla.PantallaCompleta;
import android.content.*;
import android.view.*;

public class AndroidLauncher extends AndroidApplication {

	private WakeLock wakeLock;

	private PantallaCompleta pantallaCompleta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		pantallaCompleta = new PantallaCompleta(this);

		pantallaCompleta.pantallaCompleta();

		pantallaCompleta.ocultarBotonesVirtuales();

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		initialize(new Helicoptero(), config);

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
