package com.diamon.helicoptero.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.diamon.helicoptero.Helicoptero;

public class PruebaHelicoptero {
	public static void main(String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.addIcon("textura/icono.png", FileType.Internal);
		
		config.resizable =false;

		new LwjglApplication(new Helicoptero(), config);
	}
}
