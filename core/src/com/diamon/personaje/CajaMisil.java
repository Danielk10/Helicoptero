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

public class CajaMisil extends Personaje {

	private float tiemo;

	private float velocidadY;

	private float pocicionY;

	private float distanciaMovimientoY;

	private int tipoitem;

	public CajaMisil(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);
		// TODO Auto-generated constructor stub
	}

	public CajaMisil(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);
		// TODO Auto-generated constructor stub
	}

	public CajaMisil(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actualizar(float delta) {

		if (x <= camara.position.x + Juego.ANCHO_PANTALLA / 2) {

			super.actualizar(delta);

			tiemo += delta;

			y = pocicionY + distanciaMovimientoY + (distanciaMovimientoY * MathUtils.sinDeg(tiemo * velocidadY));

		}

		if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

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

	public float getDistanciaMovimientoY() {
		return distanciaMovimientoY;
	}

	public void setDistanciaMovimientoY(float distanciaMovimientoY) {
		this.distanciaMovimientoY = distanciaMovimientoY;
	}

	public int getTipoitem() {
		return tipoitem;
	}

	public void setTipoitem(int tipoitem) {
		this.tipoitem = tipoitem;
	}

	private void agregarItem() {

		ItemDeJuego item = new ItemDeJuego(
				recurso.get("textura/elirecursos.atlas", TextureAtlas.class).getRegions().get(0), pantalla);

		item.setSize(24, 24);

		item.setPosition(x, y);

		item.setVelocidad(3);

		item.setItem(ItemDeJuego.MISIL);

		item.setDuracionQuieto(1);

		personajes.add(item);

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

		if (actor instanceof Jugador || actor instanceof Misil || actor instanceof Bomba || actor instanceof Bala
				|| actor instanceof Satelite || actor instanceof BalaExplosiva) {

			if (dato.isSonido())

			{

				recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

			}

			explosion();

			agregarItem();

			remover = true;

		}

		if (actor instanceof ExplosionTerreno) {

			if (actor.getWidth() >= 64) {

				agregarItem();

				remover = true;

			}

		}

	}

}