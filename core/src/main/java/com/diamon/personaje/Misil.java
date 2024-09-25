package com.diamon.personaje;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class Misil extends Personaje {

	public static final float VELOCIDAD_BALA = 10.0F;

	private float velocidad;

	public static final int DERECHO_ARRIBA = 0;
	public static final int DERECHO_ABAJO = 1;
	public static final int DERECHO = 2;
	public static final int IZQUIERDO_ARRIBA = 3;
	public static final int IZQUIERDO_ABAJO = 4;
	public static final int IZQUIERDO = 5;
	public static final int ARRIBA = 6;
	public static final int ABAJO = 7;

	public static final int NORMAL = 8;
	public static final int EXPLOSIVA = 9;
	public static final int LASER = 10;
	public static final int EXTRATERRESTRE = 11;

	private int lado;

	private int tipo;

	private int puntos;

	public Misil(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

		velocidad = VELOCIDAD_BALA;

		lado = Misil.IZQUIERDO;

		tipo = Misil.NORMAL;
	}

	public Misil(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

		velocidad = VELOCIDAD_BALA;

		lado = Misil.IZQUIERDO;

		tipo = Misil.NORMAL;
	}

	public Misil(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, Animation.PlayMode modo, Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

		velocidad = VELOCIDAD_BALA;

		lado = Misil.IZQUIERDO;

		tipo = Misil.NORMAL;
	}

	public void setVelocidad(float velocidad) {

		this.velocidad = velocidad;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getLado() {
		return lado;
	}

	public void setLado(int lado) {
		this.lado = lado;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public float getVelocidad() {
		return velocidad;
	}

	@Override
	public void actualizar(float delta) {
		super.actualizar(delta);

		if (lado == Misil.DERECHO) {
			x += velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x >= camara.position.x + Juego.ANCHO_PANTALLA / 2) {

				remover = true;
			}

		}

		if (lado == Misil.IZQUIERDO) {

			x -= velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

				remover = true;
			}

		}

		if (lado == Misil.ARRIBA) {

			y += velocidad / Juego.DELTA_A_PIXEL * delta;

			if (y >= camara.position.y + Juego.ALTO_PANTALLA / 2) {

				remover = true;
			}

		}

		if (lado == Misil.ABAJO) {

			y -= velocidad / Juego.DELTA_A_PIXEL * delta;

			if (y <= camara.position.y - (Juego.ALTO_PANTALLA / 2 + getHeight())) {

				remover = true;
			}

		}
	}

	@Override
	public void colision(Personaje actor) {

		if (actor instanceof PlatilloA || actor instanceof PlatilloDeLuz || actor instanceof PlatilloVolador
				|| actor instanceof NaveFUno || actor instanceof NaveFDos || actor instanceof NaveFTres
				|| actor instanceof NaveFCuatro || actor instanceof NaveFCinco || actor instanceof NaveFSeis
				|| actor instanceof NaveFSiete || actor instanceof NaveFOcho || actor instanceof NaveFNueve
				|| actor instanceof NaveFDiez || actor instanceof NaveFOnce || actor instanceof NaveFDoce
				|| actor instanceof NaveFTrece || actor instanceof NaveFCatorce || actor instanceof NaveFQuince
				|| actor instanceof NaveFDiesciceis || actor instanceof AntiAereo || actor instanceof Robot
				|| actor instanceof MaquinaParedDerecha || actor instanceof MaquinaParedIzquierda
				|| actor instanceof CajaVida || actor instanceof CajaBomba || actor instanceof CajaMisil
				|| actor instanceof CajaVelocidad || actor instanceof CajaSatelite
				|| actor instanceof CajaHelicopteroNormal | actor instanceof CajaHelicopteroVerde
				|| actor instanceof CajaHelicopteroRedondo || actor instanceof CajaHelicopteroMedico
				|| actor instanceof CajaHelicopteroNegro || actor instanceof CajaHelicopteroSatelital
				|| actor instanceof CarroGris || actor instanceof CarroAmarillo || actor instanceof CamionetaVerde
				|| actor instanceof CamionetaGris || actor instanceof CamionetaCarga || actor instanceof BarcoVerde
				|| actor instanceof Sierra || actor instanceof SateliteEnemigo) {
			if (dato.isSonido())

			{
				recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

			}
			puntos = 5;

			remover = true;
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

			}

			remover = true;
		}

		if (actor instanceof Terreno) {

			if (dato.isSonido())

			{
				recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

			}

			explosionTerreno();

			remover = true;
		}

	}

	public void explosion() {

		Explosion explosion = new Explosion(recurso.get("textura/explosion1.atlas", TextureAtlas.class).getRegions(),
				0.07f, Animation.PlayMode.NORMAL, pantalla);

		explosion.setDuracionExplosion(0.3f);

		explosion.setSize(64, 64);

		explosion.setPosition(x, y);

		personajes.add(explosion);
	}

	public void explosionTerreno() {

		ExplosionTerreno explosion = new ExplosionTerreno(
				recurso.get("textura/explosion1.atlas", TextureAtlas.class).getRegions(), 0.07f,
				Animation.PlayMode.NORMAL, pantalla);

		explosion.setSize(64, 64);

		explosion.setPosition(x + 5, y);

		explosion.setDuracionExplosion(0.3f);

		personajes.add(explosion);
	}

}
