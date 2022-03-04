package com.diamon.publicidad;

import android.app.Activity;
import android.content.Intent;

import com.diamon.helicoptero.Publicidad;
import com.diamon.terminos.Terminos;

import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.ads.banner.Banner;

public class MostrarPublicidad implements Publicidad {

	private Activity actividad;

	private Banner baner;

	public MostrarPublicidad(Activity actividad) {

		this.actividad = actividad;

		baner = new Banner(actividad);

		baner.setScaleX(0.5f);

		baner.setScaleY(0.5f);

	}

	public Banner getBanner() {

		return baner;

	}

	public static void IniciarPublicidad(Activity actividad) {

		StartAppSDK.init(actividad, Publicidad.ID_DE_APLICACION, Publicidad.ANUNCIOS_DE_DEVOLUCION);

		StartAppAd.disableSplash();

		StartAppAd.disableAutoInterstitial();

	}

	@Override
	public void mostrarInterstitial() {

		// StartAppAd.disbleAutoInterstitial();

		StartAppAd.showAd(actividad);

		// StartAppAd.enableAutoInterstitial();

	}

	@Override
	public void cargarBanner() {

		baner.loadAd();

	}

	@Override
	public void mostrarBanner() {

		baner.showBanner();

	}

	@Override
	public void ocultarBanner() {

		baner.hideBanner();

	}

	@Override
	public void iniciarActividad() {

		Intent nuevaActividad = new Intent(actividad, Terminos.class);

		actividad.startActivity(nuevaActividad);

	}

	@Override
	public void botonAtrasInterstitial() {

		StartAppAd.onBackPressed(actividad);

	}

}
