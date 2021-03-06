package com.diamon.personaje;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class Robot extends Personaje {

	private Animation<TextureRegion> animacion1;

	private Animation<TextureRegion> animacion2;

	private float tiempoCuadro;

	private float duracionDisparo;

	private Jugador jugador;

	public Robot(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

		obtenerJugador();

		duracionDisparo = Juego.DELTA_A_PIXEL;

	}

	public Robot(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

		obtenerJugador();

		duracionDisparo = Juego.DELTA_A_PIXEL;

	}

	public Robot(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

		obtenerJugador();

		animacion1 = new Animation<TextureRegion>(tiempoAnimacion, texturaRegion.get(0));

		animacion1.setPlayMode(PlayMode.NORMAL);

		animacion = animacion1;

		animacion2 = new Animation<TextureRegion>(tiempoAnimacion, texturaRegion);

		animacion2.setPlayMode(modo);

		duracionDisparo = Juego.DELTA_A_PIXEL;

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

			if (jugador.getY() <= y + getHeight() && jugador.getY() + jugador.getHeight() >= y && jugador.getX() <= x)

			{
				if (animacion2 != null) {

					animacion = animacion2;

				}

				if (tiempoCuadro / duracionDisparo >= 1) {

					disparar(BalaEnemigo.IZQUIERDO);

					tiempoCuadro = 0;

				}

			} else

			{

				if (animacion1 != null) {

					animacion = animacion1;

				}

			}

		}

		if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

			remover = true;

		}

	}

	public float getDuracionDisparo() {
		return duracionDisparo;
	}

	public void setDuracionDisparo(float duracionDisparo) {
		this.duracionDisparo = duracionDisparo;
	}

	public void disparar(int lado) {

		BalaEnemigo bala = new BalaEnemigo(recurso.get("textura/bala.png", Texture.class), pantalla);

		bala.setSize(12F, 12f);

		bala.setPosition(x, y + (getHeight() / 2 + 6));

		bala.setVelocidad(4);

		bala.setLado(lado);

		if (dato.isSonido())

		{

			recurso.get("audio/disparoRobot.ogg", Sound.class).play(dato.getVolumenSonido());

		}

		personajes.add(bala);

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

		if (actor instanceof ExplosionTerreno) {

			if (actor.getWidth() >= 64) {

				remover = true;

			}

		}

	}

}
