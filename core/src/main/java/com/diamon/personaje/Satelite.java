package com.diamon.personaje;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class Satelite extends Personaje {

	private float tiempoCuadro;

	private float duracionDisparo;

	private float tiempo;

	private float velocidadY;

	private float distanciaMovimiento;

	private int puntos;

	private Jugador jugador;

	public Satelite(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

	}

	public Satelite(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

	}

	public Satelite(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, PlayMode modo, Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
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

		if (jugador != null) {

			tiempoCuadro += delta;

			if (tiempoCuadro / 1 >= 1) {

				tiempoCuadro = 0;

			}

			tiempo += delta;

			x = (jugador.getX() + jugador.getWidth() / 2)
					+ (distanciaMovimiento * MathUtils.cosDeg(tiempo * velocidadY));

			y = (jugador.getY() + jugador.getHeight() / 2)
					+ (distanciaMovimiento * MathUtils.sinDeg(tiempo * velocidadY));

			if (!jugador.isVivo()) {

				remover = true;

			}

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

		if (actor instanceof PlatilloVolador || actor instanceof NaveFUno || actor instanceof AntiAereo
				|| actor instanceof PlatilloA || actor instanceof Robot || actor instanceof CajaHelicopteroRedondo
				|| actor instanceof CajaBomba || actor instanceof CajaMisil || actor instanceof CajaSatelite
				|| actor instanceof CajaVelocidad || actor instanceof CajaVida
				|| actor instanceof CajaHelicopteroSatelital || actor instanceof CajaHelicopteroNormal) {

			puntos = 5;

		}

		if (actor instanceof SateliteEnemigo) {

			if (dato.isSonido())

			{
				recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

			}

			puntos = 5;

		}

		if (actor instanceof JefeUno || actor instanceof JefeDos || actor instanceof JefeTres
				|| actor instanceof JefeCuatro) {

			if (dato.isSonido())

			{
				recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

			}

			explosion();

			if (actor.getDureza() <= 0)

			{

				puntos = 1000;

			} else {

				puntos = 5;

			}

			remover = true;
		}

		if (actor instanceof Sierra) {

			puntos = 5;

			remover = true;

		}

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
