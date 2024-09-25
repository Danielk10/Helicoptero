package com.diamon.personaje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class BolaPlasma extends Personaje {

	public static final float VELOCIDAD_BALA = 5.0F;

	private float velocidad;

	public static final int DERECHO_ARRIBA = 0;
	public static final int DERECHO_ABAJO = 1;
	public static final int DERECHO = 2;
	public static final int IZQUIERDO_ARRIBA = 3;
	public static final int IZQUIERDO_ABAJO = 4;
	public static final int IZQUIERDO = 5;
	public static final int ARRIBA = 6;
	public static final int ABAJO = 7;

	public static final int NORMAL = 8;
	public static final int EXPLOSIVA = 9;
	public static final int LASER = 10;

	private int lado;

	private int tipo;

	public BolaPlasma(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

		velocidad = VELOCIDAD_BALA;

		lado = BalaEnemigo.IZQUIERDO;

		tipo = BalaEnemigo.NORMAL;
	}

	public BolaPlasma(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

		velocidad = VELOCIDAD_BALA;

		lado = BalaEnemigo.IZQUIERDO;

		tipo = BalaEnemigo.NORMAL;
	}

	public BolaPlasma(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, Animation.PlayMode modo,
			Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

		velocidad = VELOCIDAD_BALA;

		lado = BalaEnemigo.IZQUIERDO;

		tipo = BalaEnemigo.NORMAL;
	}

	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		if (lado == Bala.DERECHO) {
			x += velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x >= camara.position.x + Juego.ANCHO_PANTALLA / 2) {

				remover = true;
			}

		}

		if (lado == Bala.IZQUIERDO) {

			x -= velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

				remover = true;
			}

		}

		if (lado == Bala.ARRIBA) {

			y += velocidad / Juego.DELTA_A_PIXEL * delta;

			if (y >= camara.position.y + Juego.ALTO_PANTALLA / 2) {

				remover = true;
			}

		}

		if (lado == Bala.ABAJO) {

			y -= velocidad / Juego.DELTA_A_PIXEL * delta;

			if (y <= camara.position.y - (Juego.ALTO_PANTALLA / 2 + getHeight())) {

				remover = true;
			}

		}

		if (lado == Bala.IZQUIERDO_ABAJO) {

			x -= velocidad / Juego.DELTA_A_PIXEL * delta;

			y -= velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())
					|| y <= camara.position.y - (Juego.ALTO_PANTALLA / 2 + getHeight())) {

				remover = true;

			}

		}

		if (lado == Bala.IZQUIERDO_ARRIBA) {

			x -= velocidad / Juego.DELTA_A_PIXEL * delta;

			y += velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())
					|| y >= camara.position.y + Juego.ALTO_PANTALLA / 2) {

				remover = true;
			}

		}

		if (lado == Bala.DERECHO_ABAJO) {

			x += velocidad / Juego.DELTA_A_PIXEL * delta;

			y -= velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x >= camara.position.x + Juego.ANCHO_PANTALLA / 2
					|| y <= camara.position.y - (Juego.ALTO_PANTALLA / 2 + getHeight())) {

				remover = true;
			}

		}

		if (lado == Bala.DERECHO_ARRIBA) {

			x += velocidad / Juego.DELTA_A_PIXEL * delta;

			y += velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x >= camara.position.x + Juego.ANCHO_PANTALLA / 2 || y >= camara.position.y + Juego.ALTO_PANTALLA / 2) {

				remover = true;
			}

		}

	}

	public float getVelocidad() {
		return velocidad;
	}

	public int getLado() {
		return lado;
	}

	public void setLado(int lado) {
		this.lado = lado;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public void explosion() {

		Explosion explosion = new Explosion(recurso.get("textura/explosion1.atlas", TextureAtlas.class).getRegions(),
				0.07f, Animation.PlayMode.NORMAL, pantalla);

		explosion.setSize(64, 64);

		explosion.setPosition(x, y);

		explosion.setDuracionExplosion(0.58f);

		personajes.add(explosion);
	}

	@Override
	public void colision(Personaje actor) {

		if (actor instanceof Jugador || actor instanceof Satelite) {

			explosion();

			remover = true;
		}
	}
}
