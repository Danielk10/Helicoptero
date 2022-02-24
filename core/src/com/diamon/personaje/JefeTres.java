package com.diamon.personaje;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class JefeTres extends Personaje {

	private float duracionDisparo;

	private float tiempoCuadro;

	private float tiemo;

	private float velocidadY;

	private float velocidadX;

	private float distanciaMovimientoY;

	private float pocicionY;

	private Jugador jugador;

	public JefeTres(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

		obtenerJugador();

	}

	public JefeTres(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

		obtenerJugador();

	}

	public JefeTres(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla) {
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

			x += velocidadX / Juego.DELTA_A_PIXEL * delta;

			if (x >= camara.position.x + (Juego.ANCHO_PANTALLA / 2 - getWidth())
					|| x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 - 200)) {

				velocidadX = -velocidadX;

			}

			tiempoCuadro += delta;

			if (tiempoCuadro / duracionDisparo >= 1) {

				if (jugador.getY() <= y + getHeight() && jugador.getY() + jugador.getHeight() >= y
						&& jugador.getX() <= x)

				{

					disparar(BalaEnemigo.IZQUIERDO);

				}

				if (jugador.getY() <= y + getHeight() && jugador.getY() + jugador.getHeight() >= y
						&& jugador.getX() >= x + getWidth())

				{

					disparar(BalaEnemigo.DERECHO);

				}

				tiempoCuadro = 0;
			}

		}

		if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

			remover = true;

		}

	}

	public void disparar(int lado) {

		BolaPlasma plasma = new BolaPlasma(recurso.get("textura/bolaPlasma.atlas", TextureAtlas.class).getRegions(),
				0.05f, Animation.PlayMode.LOOP, pantalla);

		plasma.setSize(64, 64);

		if (BalaEnemigo.IZQUIERDO == lado) {

			plasma.setPosition(x - 64, y + this.getHeight() / 2 - 32);

		}

		if (BalaEnemigo.DERECHO == lado) {

			plasma.setPosition(x + this.getWidth(), y + this.getHeight() / 2 - 32);

		}

		plasma.setLado(lado);

		plasma.setVelocidad(8);

		personajes.add(plasma);

		if (dato.isSonido())

		{

			recurso.get("audio/disparoEnemigo.ogg", Sound.class).play(dato.getVolumenSonido());

		}

	}

	public void explosion() {

		Explosion explosion = new Explosion(recurso.get("textura/explosion1.atlas", TextureAtlas.class).getRegions(),
				0.07f, Animation.PlayMode.NORMAL, pantalla);

		explosion.setSize(128, 128);

		explosion.setPosition(x, y);

		explosion.setDuracionExplosion(0.58f);

		personajes.add(explosion);

	}

	public float getDuracionDisparo() {
		return duracionDisparo;
	}

	public void setDuracionDisparo(float duracionDisparo) {
		this.duracionDisparo = duracionDisparo;
	}

	@Override
	public Rectangle getBoundingRectangle() {

		Rectangle r = super.getBoundingRectangle();

		return r;
	}

	@Override
	public void colision(Personaje actor) {

		if (actor instanceof Jugador) {

			if (dureza <= 0) {

				if (dato.isSonido())

				{

					recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

				}

				explosion();

				remover = true;

			}

		}

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

		if (actor instanceof Misil || actor instanceof Satelite) {

			dureza -= 5;

			if (dureza <= 0) {

				if (dato.isSonido())

				{
					recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

				}

				explosion();

				remover = true;

			}

		}

		if (actor instanceof Bomba) {

			dureza -= 10;

			if (dureza <= 0) {

				if (dato.isSonido())

				{
					recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

				}

				explosion();

				remover = true;

			}

		}

		if (actor instanceof ExplosionTerreno) {

			if (actor.getWidth() >= 64) {

				dureza -= 5;

				if (dureza <= 0) {

					if (dato.isSonido())

					{
						recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

					}

					explosion();

					remover = true;

				}

			}

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
