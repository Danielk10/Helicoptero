package com.diamon.personaje;

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

public class SateliteEnemigo extends Personaje {

	private float tiempoCuadro;

	private float duracionDisparo;

	private float tiempo;

	private float velocidadY;

	private float distanciaMovimiento;

	private int puntos;

	private Personaje personaje;

	public SateliteEnemigo(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

	}

	public SateliteEnemigo(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

	}

	public SateliteEnemigo(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla) {
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

		if (personaje != null) {

			tiempoCuadro += delta;

			if (tiempoCuadro / 1 >= 1) {

				tiempoCuadro = 0;

			}

			tiempo += delta;

			x = (personaje.getX() + personaje.getWidth() / 2)
					+ (distanciaMovimiento * MathUtils.cosDeg(tiempo * velocidadY));

			y = (personaje.getY() + personaje.getHeight() / 2)
					+ (distanciaMovimiento * MathUtils.sinDeg(tiempo * velocidadY));

			if (personaje.isRemover()) {

				remover = true;

			}

		}

		if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

			remover = true;

		}

	}

	public float getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(float velocidadY) {
		this.velocidadY = velocidadY;
	}

	public float getDistanciaMovimiento() {
		return distanciaMovimiento;
	}

	public void setDistanciaMovimiento(float distanciaMovimiento) {
		this.distanciaMovimiento = distanciaMovimiento;
	}

	@Override
	public void colision(Personaje actor) {

		if (actor instanceof Jugador || actor instanceof Misil || actor instanceof Bomba || actor instanceof Satelite) {

			explosion();

			remover = true;

		}

		if (actor instanceof ExplosionTerreno) {

			if (actor.getWidth() >= 64) {

				remover = true;

			}

		}

	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public float getDuracionDisparo() {
		return duracionDisparo;
	}

	public void setDuracionDisparo(float duracionDisparo) {
		this.duracionDisparo = duracionDisparo;
	}

	public void explosion() {

		Explosion explosion = new Explosion(recurso.get("textura/explosion1.atlas", TextureAtlas.class).getRegions(),
				0.07f, Animation.PlayMode.NORMAL, pantalla);

		explosion.setSize(64, 64);

		explosion.setPosition(x, y);

		explosion.setDuracionExplosion(0.58f);

		personajes.add(explosion);
	}

	public void disparar() {

		Bala bala = new Bala(recurso.get("textura/bala.png", Texture.class), pantalla);

		bala.setSize(8, 8);

		bala.setVelocidad(10);

		bala.setPosition(x, y);

		bala.setLado(Bala.DERECHO);

		personajes.add(bala);

	}

}