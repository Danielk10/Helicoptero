package com.diamon.personaje;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class PlatilloVolador extends Personaje {

	public static final int VELOCIDAD_MAQUINA = 5;

	private float tiempoCuadro;

	private float duracionDisparo;

	private float velocidadY;

	private float pocicionY;

	private float distanciaDesplazamientoY;

	private Jugador jugador;

	public PlatilloVolador(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

		velocidadY = 0;

		obtenerJugador();

	}

	public PlatilloVolador(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

		velocidadY = 0;

		obtenerJugador();

	}

	public PlatilloVolador(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, Animation.PlayMode modo,
			Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

		velocidadY = 0;

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

			tiempoCuadro += delta;

			if (tiempoCuadro / duracionDisparo >= 1) {

				if (MathUtils.random() < 0.8f) {

					if (x > jugador.getX())

					{

						disparar();
					}

				}

				tiempoCuadro = 0;

			}

			x -= PlatilloVolador.VELOCIDAD_MAQUINA / Juego.DELTA_A_PIXEL * delta;

			y += velocidadY / Juego.DELTA_A_PIXEL * delta;

			if (y <= (camara.position.y - Juego.ALTO_PANTALLA / 2) + pocicionY
					|| y >= pocicionY + distanciaDesplazamientoY) {

				velocidadY = -velocidadY;

			}

		}

		if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

			remover = true;

		}

	}

	public void disparar() {

		BalaEnemigo bala = new BalaEnemigo(recurso.get("textura/bala.png", Texture.class), pantalla);

		bala.setSize(8.0F, 8.0F);

		bala.setPosition(x, y);

		bala.setLado(BalaEnemigo.IZQUIERDO);

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

	public float getDistanciaDesplazamientoY() {
		return distanciaDesplazamientoY;
	}

	public void setDistanciaDesplazamientoY(float distanciaDesplazamientoY) {
		this.distanciaDesplazamientoY = distanciaDesplazamientoY;
	}

	public float getDuracionDisparo() {
		return duracionDisparo;
	}

	public void setDuracionDisparo(float duracionDisparo) {
		this.duracionDisparo = duracionDisparo;
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
}
