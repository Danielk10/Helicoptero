package com.diamon.personaje;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class BarcoVerde extends Personaje {

	public BarcoVerde(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

	}

	public BarcoVerde(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

	}

	public BarcoVerde(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, Animation.PlayMode modo,
			Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

	}

	@Override
	public void actualizar(float delta) {

		if (x <= camara.position.x + Juego.ANCHO_PANTALLA / 2) {

			super.actualizar(delta);

		}

		if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

			remover = true;

		}

	}

	public void explosion() {

		Explosion explosion = new Explosion(recurso.get("textura/explosion1.atlas", TextureAtlas.class).getRegions(),
				0.07f, Animation.PlayMode.NORMAL, pantalla);

		explosion.setSize(256, 256);

		explosion.setPosition(x, y);

		explosion.setDuracionExplosion(0.58f);

		personajes.add(explosion);
	}

	@Override
	public void colision(Personaje actor) {

		if (actor instanceof Jugador) {

			if (((Jugador) actor).isInmune()) {

				if (dato.isSonido())

				{

					recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

				}

			}

			if (!(((Jugador) actor).getVida() <= 0)) {

				explosion();

			}

			remover = true;

		}

		if (actor instanceof Misil || actor instanceof Bomba) {

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

	@Override
	public Rectangle getBoundingRectangle() {

		Rectangle r = super.getBoundingRectangle();

		r.height = 45;

		return r;
	}

}