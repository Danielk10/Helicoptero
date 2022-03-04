package com.diamon.helicoptero.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.diamon.helicoptero.Helicoptero;
import com.diamon.helicoptero.Publicidad;

public class DesktopLauncher {

	public static void main(String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.addIcon("textura/icono.png", FileType.Internal);

		config.resizable = false;

		new LwjglApplication(new Helicoptero(new Publicidad() {

			@Override
			public void mostrarInterstitial() {

			}

			@Override
			public void botonAtrasInterstitial() {

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

			}
		}), config);
	}
}
