package com.diamon.personaje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class NubeTres extends Personaje {

	private float velocidadX;

	public static final float VELOCIDAD = 0.5F;

	public NubeTres(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);
		velocidadX = VELOCIDAD;
	}

	public NubeTres(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);
		velocidadX = VELOCIDAD;
	}

	public NubeTres(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, Animation.PlayMode modo,
			Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);
		velocidadX = VELOCIDAD;
	}

	@Override
	public void actualizar(float delta) {

		if (x <= camara.position.x + Juego.ANCHO_PANTALLA / 2) {

			super.actualizar(delta);

			x -= velocidadX / Juego.DELTA_A_PIXEL * delta;

		}

		if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

			remover = true;

		}

	}

	@Override
	public void colision(Personaje actor) {
	}
}