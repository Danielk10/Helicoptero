package com.diamon.personaje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class ExplosionTerreno extends Personaje {

	private float tiemoExplosion;

	private float duracionExplosion;

	private int puntos;

	public ExplosionTerreno(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

	}

	public ExplosionTerreno(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

	}

	public ExplosionTerreno(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	@Override
	public void actualizar(float delta) {
		super.actualizar(delta);

		tiemoExplosion += delta;

		if (tiemoExplosion / duracionExplosion >= 1) {

			humo();

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

	public void humo() {

		Humo humo = new Humo(recurso.get("textura/humo.atlas", TextureAtlas.class).getRegions(), 0.07f,
				Animation.PlayMode.LOOP, pantalla);

		humo.setDuracionHumo(2);

		humo.setSize(32, 64);

		humo.setPosition(x, y);

		personajes.add(humo);
	}

	@Override
	public void colision(Personaje actor) {

		if (actor instanceof PlatilloVolador || actor instanceof NaveFUno || actor instanceof AntiAereo
				|| actor instanceof PlatilloA || actor instanceof Sierra || actor instanceof Robot
				|| actor instanceof CajaHelicopteroRedondo) {

			puntos = 5;

		}

	}

}
