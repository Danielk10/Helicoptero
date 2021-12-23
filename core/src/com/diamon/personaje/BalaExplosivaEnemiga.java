package com.diamon.personaje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class BalaExplosivaEnemiga extends Personaje {

	public static final float VELOCIDAD_BOMBA = 10.0F;

	private float velocidadX;

	private float velocidadY;

	private int lado;

	public static final int DERECHO_ARRIBA = 0;
	public static final int DERECHO_ABAJO = 1;
	public static final int DERECHO = 2;
	public static final int IZQUIERDO_ARRIBA = 3;
	public static final int IZQUIERDO_ABAJO = 4;
	public static final int IZQUIERDO = 5;
	public static final int ARRIBA = 6;
	public static final int ABAJO = 7;

	public BalaExplosivaEnemiga(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

		velocidadX = 0;

		velocidadY = 0;
	}

	public BalaExplosivaEnemiga(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

		velocidadX = 0;

		velocidadY = 0;
	}

	public BalaExplosivaEnemiga(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, Animation.PlayMode modo,
			Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

		velocidadX = 0;

		velocidadY = 0;
	}

	@Override
	public void actualizar(float delta) {
		super.actualizar(delta);

		x += velocidadX / Juego.DELTA_A_PIXEL * delta;

		y += velocidadY / Juego.DELTA_A_PIXEL * delta;

		if (y <= camara.position.y - Juego.ALTO_PANTALLA / 2 + getHeight()
				|| y >= camara.position.y + Juego.ALTO_PANTALLA / 2
				|| x <= camara.position.x - Juego.ANCHO_PANTALLA / 2 + getWidth()
				|| x >= camara.position.x + Juego.ANCHO_PANTALLA / 2) {
			remover = true;
		}
	}

	public int getLado() {
		return lado;
	}

	public void setLado(int lado) {

		switch (lado) {

		case IZQUIERDO_ARRIBA:
			velocidadX = -VELOCIDAD_BOMBA;
			velocidadY = VELOCIDAD_BOMBA;
			break;

		case ARRIBA:
			velocidadX = 0;
			velocidadY = VELOCIDAD_BOMBA;
			break;

		case DERECHO_ARRIBA:
			velocidadX = VELOCIDAD_BOMBA;
			velocidadY = VELOCIDAD_BOMBA;
			break;

		case IZQUIERDO:
			velocidadX = -VELOCIDAD_BOMBA;
			velocidadY = 0;
			break;

		case DERECHO:
			velocidadX = VELOCIDAD_BOMBA;
			velocidadY = 0;
			break;

		case IZQUIERDO_ABAJO:
			velocidadX = -VELOCIDAD_BOMBA;
			velocidadY = -VELOCIDAD_BOMBA;
			break;

		case ABAJO:
			velocidadX = 0;
			velocidadY = -VELOCIDAD_BOMBA;
			break;

		case DERECHO_ABAJO:
			velocidadX = VELOCIDAD_BOMBA;
			velocidadY = -VELOCIDAD_BOMBA;
			break;
		}
	}

	@Override
	public void colision(Personaje actor) {

		if (actor instanceof Jugador || actor instanceof Satelite) {

			remover = true;
		}
	}
}
