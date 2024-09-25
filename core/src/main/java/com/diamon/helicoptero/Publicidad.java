package com.diamon.helicoptero;

public interface Publicidad {

	public static final boolean ANUNCIOS_DE_DEVOLUCION = false;

	public static final String ID_DE_APLICACION = "203801004";

	public void mostrarInterstitial();

	public void botonAtrasInterstitial();
	
	public void cargarBanner();

	public void mostrarBanner();

	public void ocultarBanner();

	public void iniciarActividad();

}
