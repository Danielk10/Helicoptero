package com.diamon.personaje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class Humo extends Personaje {

	private float tiemoHumo;

	private float duracionHumo;

	public Humo(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

	}

	public Humo(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

	}

	public Humo(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

	}

	@Override
	public void actualizar(float delta) {
		super.actualizar(delta);

		tiemoHumo += delta;

		if (tiemoHumo / duracionHumo >= 1) {

			remover = true;

			tiemoHumo = 0;
		}

	}

	public float getDuracionHumo() {
		return duracionHumo;
	}

	public void setDuracionHumo(float duracionHumo) {
		this.duracionHumo = duracionHumo;
	}

	@Override
	public void colision(Personaje personaje) {
		// TODO Auto-generated method stub

	}

}
