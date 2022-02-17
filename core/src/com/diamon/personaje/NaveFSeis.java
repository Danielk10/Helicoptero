package com.diamon.personaje;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class NaveFSeis extends Personaje {

	private float tiemo;

	private float velocidadY;

	private float velocidadX;

	private float distanciaMovimientoY;

	private float pocicionY;

	private float tiempoCuadro;

	private float duracionDisparo;

	private int lado;

	public static final int IZQUIERDO = 0;

	public static final int DERECHO = 1;

	private Jugador jugador;

	public NaveFSeis(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

		obtenerJugador();

	}

	public NaveFSeis(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

		obtenerJugador();

	}

	public NaveFSeis(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

		obtenerJugador();

	}

	private void obtenerJugador() {

		for (int i = 0; i < personajes.size; i++) {

			if (personajes.get(i) instanceof Jugador) {

				jugador = (Jugador) personajes.get(i);

			}

		}

	}

	@Override
	public void actualizar(float delta) {

		if (x <= camara.position.x + Juego.ANCHO_PANTALLA / 2) {

			super.actualizar(delta);

			tiemo += delta;

			y = pocicionY + distanciaMovimientoY + (distanciaMovimientoY * MathUtils.sinDeg(tiemo * velocidadY));

			if (lado == NaveFUno.IZQUIERDO) {

				x -= velocidadX / Juego.DELTA_A_PIXEL * delta;

				if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

					remover = true;

				}

			}

			if (jugador.getY() <= y + getHeight() && jugador.getY() >= y && jugador.getX() <= x)

			{
				tiempoCuadro += delta;

				if (tiempoCuadro / duracionDisparo >= 1) {

					disparar(BalaEnemigo.IZQUIERDO);
					tiempoCuadro = 0;
				}

			}

			if (jugador.getY() <= y + getHeight() && jugador.getY() >= y && jugador.getX() >= x + getWidth())

			{

				tiempoCuadro += delta;

				if (tiempoCuadro / duracionDisparo >= 1) {

					disparar(BalaEnemigo.DERECHO);
					tiempoCuadro = 0;
				}

			}

		}

		if (lado == NaveFUno.DERECHO) {

			x += velocidadX / Juego.DELTA_A_PIXEL * delta;

			if (x >= camara.position.x + Juego.ANCHO_PANTALLA / 2) {

				remover = true;

			}

		}

	}

	public int getLado() {
		return lado;
	}

	public void setLado(int lado) {
		this.lado = lado;
	}

	public float getDuracionDisparo() {
		return duracionDisparo;
	}

	public void setDuracionDisparo(float duracionDisparo) {
		this.duracionDisparo = duracionDisparo;
	}

	public void disparar(int lado) {

		BalaEnemigo bala = new BalaEnemigo(recurso.get("textura/bala.png", Texture.class), pantalla);

		bala.setSize(8.0F, 8.0F);

		if (BalaEnemigo.IZQUIERDO == lado) {

			bala.setPosition(x, y + this.getHeight() / 2);

		}

		if (BalaEnemigo.DERECHO == lado) {

			bala.setPosition(x + this.getWidth(), y + this.getHeight() / 2);

		}

		bala.setLado(lado);

		bala.setVelocidad(8);

		personajes.add(bala);

		if (dato.isSonido())

		{

			recurso.get("audio/disparoEnemigo.ogg", Sound.class).play(dato.getVolumenSonido());

		}

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

		if (actor instanceof Bala || actor instanceof BalaExplosiva) {

			dureza--;

			if (dureza <= 0) {

				if (dato.isSonido())

				{
					recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

				}

				explosion();
				remover = true;
			}

		}

		if (actor instanceof Jugador || actor instanceof Misil || actor instanceof Bomba || actor instanceof Satelite) {

			if (dato.isSonido())

			{

				recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

			}

			explosion();

			remover = true;

		}
	}

	@Override
	public void setPosition(float x, float y) {

		pocicionY = y;

		super.setPosition(x, y);
	}

	public float getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(float velocidadY) {
		this.velocidadY = velocidadY;
	}

	public float getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(float velocidadX) {
		this.velocidadX = velocidadX;
	}

	public float getDistanciaMovimientoY() {
		return distanciaMovimientoY;
	}

	public void setDistanciaMovimientoY(float distanciaMovimientoY) {
		this.distanciaMovimientoY = distanciaMovimientoY;
	}

}