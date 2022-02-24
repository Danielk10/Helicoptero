package com.diamon.personaje;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class BalaExplosiva extends Personaje {

	public static final float VELOCIDAD_BALA = 7F;

	private float velocidadX;

	private float velocidadY;

	private int puntos;

	private int lado;

	public static final int DERECHO_ARRIBA = 0;
	public static final int DERECHO_ABAJO = 1;
	public static final int DERECHO = 2;
	public static final int IZQUIERDO_ARRIBA = 3;
	public static final int IZQUIERDO_ABAJO = 4;
	public static final int IZQUIERDO = 5;
	public static final int ARRIBA = 6;
	public static final int ABAJO = 7;

	public BalaExplosiva(Texture textura, Pantalla pantalla) {

		super(textura, pantalla);

		velocidadX = 0;

		velocidadY = 0;
	}

	public BalaExplosiva(TextureRegion texturaRegion, Pantalla pantalla) {

		super(texturaRegion, pantalla);

		velocidadX = 0;

		velocidadY = 0;
	}

	public BalaExplosiva(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, Animation.PlayMode modo,
			Pantalla pantalla) {

		super(texturaRegion, tiempoAnimacion, modo, pantalla);

		velocidadX = 0;

		velocidadY = 0;
	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		x += velocidadX / Juego.DELTA_A_PIXEL * delta;

		y += velocidadY / Juego.DELTA_A_PIXEL * delta;

		if (y <= camara.position.y - Juego.ALTO_PANTALLA / 2 + getHeight()
				|| y >= camara.position.y + Juego.ALTO_PANTALLA / 2
				|| x <= camara.position.x - Juego.ANCHO_PANTALLA / 2 + getWidth()
				|| x >= camara.position.x + Juego.ANCHO_PANTALLA / 2) {
			remover = true;
		}
	}

	public int getLado() {
		return lado;
	}

	public void setLado(int lado) {

		switch (lado) {

		case IZQUIERDO_ARRIBA:
			velocidadX = -VELOCIDAD_BALA;
			velocidadY = VELOCIDAD_BALA;
			break;

		case ARRIBA:
			velocidadX = 0;
			velocidadY = VELOCIDAD_BALA;
			break;

		case DERECHO_ARRIBA:
			velocidadX = VELOCIDAD_BALA;
			velocidadY = VELOCIDAD_BALA;
			break;

		case IZQUIERDO:
			velocidadX = -VELOCIDAD_BALA;
			velocidadY = 0;
			break;

		case DERECHO:
			velocidadX = VELOCIDAD_BALA;
			velocidadY = 0;
			break;

		case IZQUIERDO_ABAJO:
			velocidadX = -VELOCIDAD_BALA;
			velocidadY = -VELOCIDAD_BALA;
			break;

		case ABAJO:
			velocidadX = 0;
			velocidadY = -VELOCIDAD_BALA;
			break;

		case DERECHO_ABAJO:
			velocidadX = VELOCIDAD_BALA;
			velocidadY = -VELOCIDAD_BALA;
			break;
		}
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public void explosion() {

		Explosion explosion = new Explosion(recurso.get("textura/explosion.atlas", TextureAtlas.class).getRegions(),
				0.07f, Animation.PlayMode.NORMAL, pantalla);

		explosion.setDuracionExplosion(0.3f);

		explosion.setSize(24, 24);

		explosion.setPosition(x, y);

		personajes.add(explosion);
	}

	public void explosionTerreno() {

		ExplosionTerreno explosion = new ExplosionTerreno(
				recurso.get("textura/explosion.atlas", TextureAtlas.class).getRegions(), 0.07f,
				Animation.PlayMode.NORMAL, pantalla);

		explosion.setDuracionExplosion(0.3f);

		explosion.setSize(24, 24);

		explosion.setPosition(x, y);

		personajes.add(explosion);
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
				|| actor instanceof CajaHelicopteroNegro || actor instanceof CajaHelicopteroSatelital) {
			if (dato.isSonido())

			{
				recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

			}

			explosion();

			puntos = 5;

			remover = true;
		}

		if (actor instanceof Sierra || actor instanceof CarroGris || actor instanceof CarroAmarillo
				|| actor instanceof CamionetaVerde || actor instanceof CamionetaGris || actor instanceof CamionetaCarga
				|| actor instanceof BarcoVerde || actor instanceof SateliteEnemigo) {

			if (dato.isSonido())

			{
				recurso.get("audio/explosion.ogg", Sound.class).play(dato.getVolumenSonido());

			}

			explosion();

			puntos = 0;

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
}
