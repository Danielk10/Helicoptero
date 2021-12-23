package com.diamon.dato;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class Configuraciones {

	private static final String DATOS = ".helicoptero/datos.dat";

	private Dato datos;

	public static final int INTERNO = 0;

	public static final int LOCAL = 1;

	public Dato leerDatos(int tipoDato) {

		if (datos != null) {

			return datos;
		}

		FileHandle configuracionDato = null;

		if (tipoDato == Configuraciones.INTERNO) {

			configuracionDato = Gdx.files.internal("datos/dato.dat");

		}

		if (tipoDato == Configuraciones.LOCAL) {

			configuracionDato = Gdx.files.local(Configuraciones.DATOS);

		}

		Json json = new Json();

		if (configuracionDato.exists()) {

			try {

				String archivoCodificado = configuracionDato.readString();

				String archivoDecodificado = Base64Coder.decodeString(archivoCodificado);

				datos = json.fromJson(Dato.class, archivoDecodificado);

			} catch (Exception e) {

				datos = new Dato();

				escribirDatos(datos);
			}

		} else {

			datos = new Dato();

			escribirDatos(datos);
		}

		return datos;
	}

	public void escribirDatos(Dato dato) {

		Json json = new Json();

		FileHandle configuracionDato = null;

		configuracionDato = Gdx.files.local(Configuraciones.DATOS);

		String archivoDecodifcado = json.toJson(dato);

		String archivoCodificado = Base64Coder.encodeString(archivoDecodifcado);

		configuracionDato.writeString(archivoCodificado, false);
	}
}
