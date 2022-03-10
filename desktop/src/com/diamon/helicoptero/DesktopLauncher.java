package com.diamon.helicoptero;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.diamon.helicoptero.Helicoptero;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main(String[] arg) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowIcon(FileType.Internal, "textura/icono.png");
		config.setResizable(false);

		new Lwjgl3Application(new Helicoptero(new Publicidad() {

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
