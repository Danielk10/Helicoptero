package com.diamon.personaje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class ItemDeJuego extends Personaje {

	private final static int VELOCIDAD_ITEM = 2;

	public final static int HELICOPTERO_NORMAL = 1;

	public final static int HELICOPTERO_REDONDO = 2;

	public final static int HELICOPTERO_NEGRO = 3;

	public final static int HELICOPTERO_MEDICO = 4;

	public final static int HELICOPTERO_VERDE = 5;

	public final static int HELICOPTERO_SATELITAL = 6;

	public final static int VIDA = 7;

	public final static int VELOCIDAD = 8;

	public final static int BOMBA = 9;

	public final static int MISIL = 10;

	public final static int SATELITE = 11;

	private float tiempoCuadro;

	private float duracionQuieto;

	private boolean mover;

	private int item;

	private int velocidad;

	public ItemDeJuego(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

		mover = false;

		item = 0;

		velocidad = ItemDeJuego.VELOCIDAD_ITEM;

	}

	public ItemDeJuego(TextureRegion texturaRegion, Pantalla pantalla) {

		super(texturaRegion, pantalla);

		mover = false;

		item = 0;

		velocidad = ItemDeJuego.VELOCIDAD_ITEM;
	}

	public ItemDeJuego(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

		mover = false;

		item = 0;

		velocidad = ItemDeJuego.VELOCIDAD_ITEM;
	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		tiempoCuadro += delta;

		if (tiempoCuadro / duracionQuieto >= 1) {

			mover = true;

			tiempoCuadro = 0;
		}

		if (mover) {

			x -= velocidad / Juego.DELTA_A_PIXEL * delta;
		}

		if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

			remover = true;

		}

	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public float getDuracionQuieto() {
		return duracionQuieto;
	}

	public void setDuracionQuieto(float duracionQuieto) {
		this.duracionQuieto = duracionQuieto;
	}

	@Override
	public void colision(Personaje personaje) {

		if (personaje instanceof Jugador) {

			remover = true;

		}

	}

}
