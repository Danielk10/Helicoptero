
package com.diamon.personaje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class Explosion extends Personaje {

	private float tiemoExplosion;

	private float duracionExplosion;

	public Explosion(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

	}

	public Explosion(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

	}

	public Explosion(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, Animation.PlayMode modo,
			Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

	}

	@Override
	public void actualizar(float delta) {
		super.actualizar(delta);

		tiemoExplosion += delta;

		if (tiemoExplosion / duracionExplosion >= 1) {

			remover = true;

			tiemoExplosion = 0;
		}

	}

	public float getDuracionExplosion() {
		return duracionExplosion;
	}

	public void setDuracionExplosion(float duracionExplosion) {
		this.duracionExplosion = duracionExplosion;
	}

	@Override
	public void colision(Personaje actor) {

		if (actor instanceof Explosion) {

			if (actor.getWidth() < getWidth()) {

				actor.setRemover(true);

			}

		}

	}
}
