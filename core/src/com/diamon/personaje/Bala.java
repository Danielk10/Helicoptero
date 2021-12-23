package com.diamon.personaje;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.nucleo.Personaje;

public class Bala extends Personaje {

	public static final float VELOCIDAD_BALA = 10.0F;

	private float velocidad;

	private int lado;

	private int tipo;

	private float xJugador;

	private int puntos;

	private float tiempoCuadroDisparoBalaExplosiva;

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

	public Bala(Texture textura, Pantalla pantalla) {
		super(textura, pantalla);

		velocidad = VELOCIDAD_BALA;

		lado = Bala.IZQUIERDO;

		tipo = Bala.NORMAL;
	}

	public Bala(TextureRegion texturaRegion, Pantalla pantalla) {
		super(texturaRegion, pantalla);

		velocidad = VELOCIDAD_BALA;

		lado = Bala.IZQUIERDO;

		tipo = Bala.NORMAL;
	}

	public Bala(Array<AtlasRegion> texturaRegion, float tiempoAnimacion, Animation.PlayMode modo, Pantalla pantalla) {
		super(texturaRegion, tiempoAnimacion, modo, pantalla);

		velocidad = VELOCIDAD_BALA;

		lado = Bala.IZQUIERDO;

		tipo = Bala.NORMAL;
	}

	public float getxJugador() {
		return xJugador;
	}

	public void setxJugador(float xJugador) {
		this.xJugador = xJugador;
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

	public float getVelocidad() {
		return velocidad;
	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		if (lado == Bala.DERECHO) {
			x += velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x >= camara.position.x + Juego.ANCHO_PANTALLA / 2) {

				remover = true;
			}

		}

		if (lado == Bala.IZQUIERDO) {

			x -= velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())) {

				remover = true;
			}

		}

		if (lado == Bala.ARRIBA) {

			y += velocidad / Juego.DELTA_A_PIXEL * delta;

			if (y >= camara.position.y + Juego.ALTO_PANTALLA / 2) {

				remover = true;
			}

		}

		if (lado == Bala.ABAJO) {

			y -= velocidad / Juego.DELTA_A_PIXEL * delta;

			if (y <= camara.position.y - (Juego.ALTO_PANTALLA / 2 + getHeight())) {

				remover = true;
			}

		}

		if (lado == Bala.IZQUIERDO_ABAJO) {

			x -= velocidad / Juego.DELTA_A_PIXEL * delta;

			y -= velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())
					|| y <= camara.position.y - (Juego.ALTO_PANTALLA / 2 + getHeight())) {

				remover = true;

			}

		}

		if (lado == Bala.IZQUIERDO_ARRIBA) {

			x -= velocidad / Juego.DELTA_A_PIXEL * delta;

			y += velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x <= camara.position.x - (Juego.ANCHO_PANTALLA / 2 + getWidth())
					|| y >= camara.position.y + Juego.ALTO_PANTALLA / 2) {

				remover = true;
			}

		}

		if (lado == Bala.DERECHO_ABAJO) {

			x += velocidad / Juego.DELTA_A_PIXEL * delta;

			y -= velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x >= camara.position.x + Juego.ANCHO_PANTALLA / 2
					|| y <= camara.position.y - (Juego.ALTO_PANTALLA / 2 + getHeight())) {

				remover = true;
			}

		}

		if (lado == Bala.DERECHO_ARRIBA) {

			x += velocidad / Juego.DELTA_A_PIXEL * delta;

			y += velocidad / Juego.DELTA_A_PIXEL * delta;

			if (x >= camara.position.x + Juego.ANCHO_PANTALLA / 2 || y >= camara.position.y + Juego.ALTO_PANTALLA / 2) {

				remover = true;
			}

		}

		if (tipo == Bala.EXPLOSIVA) {

			if (x >= xJugador + 200) {

				tiempoCuadroDisparoBalaExplosiva += delta;

				if (tiempoCuadroDisparoBalaExplosiva / 0.16f >= 1) {

					balaExplosiva();

					remover = true;

					tiempoCuadroDisparoBalaExplosiva = 0;

				}
			}

		}

	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
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
				|| actor instanceof BarcoVerde) {

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

	public void balaExplosiva() {

		BalaExplosiva bala1 = new BalaExplosiva(recurso.get("textura/cursor.png", Texture.class), pantalla);

		bala1.setSize(8, 8);

		bala1.setPosition(getX(), getY());

		bala1.setLado(BalaExplosiva.ABAJO);

		BalaExplosiva bala2 = new BalaExplosiva(recurso.get("textura/cursor.png", Texture.class), pantalla);

		bala2.setSize(8, 8);

		bala2.setPosition(getX(), getY());

		bala2.setLado(BalaExplosiva.ARRIBA);

		BalaExplosiva bala3 = new BalaExplosiva(recurso.get("textura/cursor.png", Texture.class), pantalla);

		bala3.setSize(8, 8);

		bala3.setPosition(getX(), getY());

		bala3.setLado(BalaExplosiva.DERECHO);

		BalaExplosiva bala4 = new BalaExplosiva(recurso.get("textura/cursor.png", Texture.class), pantalla);

		bala4.setSize(8, 8);

		bala4.setPosition(getX(), getY());

		bala4.setLado(BalaExplosiva.DERECHO_ABAJO);

		BalaExplosiva bala5 = new BalaExplosiva(recurso.get("textura/cursor.png", Texture.class), pantalla);

		bala5.setSize(8, 8);

		bala5.setPosition(getX(), getY());

		bala5.setLado(BalaExplosiva.DERECHO_ARRIBA);

		/*
		 * BalaExplosiva bala6 = new BalaExplosiva(recurso.get("textura/cursor.png",
		 * Texture.class), pantalla);
		 * 
		 * bala6.setSize(8, 8);
		 * 
		 * bala6.setPosition(getX(), getY());
		 * 
		 * bala6.setLado(BalaExplosiva.IZQUIERDO);
		 */

		BalaExplosiva bala7 = new BalaExplosiva(recurso.get("textura/cursor.png", Texture.class), pantalla);

		bala7.setSize(8, 8);

		bala7.setPosition(getX(), getY());

		bala7.setLado(BalaExplosiva.IZQUIERDO_ABAJO);

		BalaExplosiva bala8 = new BalaExplosiva(recurso.get("textura/cursor.png", Texture.class), pantalla);

		bala8.setSize(8, 8);

		bala8.setPosition(getX(), getY());

		bala8.setLado(BalaExplosiva.IZQUIERDO_ARRIBA);

		personajes.add(bala1);
		personajes.add(bala2);
		personajes.add(bala3);
		personajes.add(bala4);
		personajes.add(bala5);
		// personajes.add(bala6);
		personajes.add(bala7);
		personajes.add(bala8);

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

		explosion.setPosition(x - explosion.getWidth(), y);

		personajes.add(explosion);
	}

}
