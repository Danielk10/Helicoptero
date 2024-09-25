package com.diamon.publicidad;

import android.app.Activity;
import android.content.Intent;

import com.diamon.helicoptero.Publicidad;
import com.diamon.terminos.Terminos;

public class MostrarPublicidad implements Publicidad {

	private Activity actividad;

	public MostrarPublicidad(Activity actividad) {

		this.actividad = actividad;

		
	}

	public void getBanner() {



	}

	public static void IniciarPublicidad(Activity actividad) {

		

	}

	@Override
	public void mostrarInterstitial() {

		

	}

	@Override
	public void cargarBanner() {

		

	}

	@Override
	public void mostrarBanner() {

		

	}

	@Override
	public void ocultarBanner() {

		

	}

	@Override
	public void iniciarActividad() {

		Intent nuevaActividad = new Intent(actividad, Terminos.class);

		actividad.startActivity(nuevaActividad);

	}

	@Override
	public void botonAtrasInterstitial() {

	

	}

}
